package com.globant.application.services.exchange;

import com.globant.application.config.Cache;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.Order;
import com.globant.domain.exchange.SalesOrder;
import com.globant.domain.exchange.Transaction;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.exchange.TransactionType;
import com.globant.domain.factories.creationorders.CryptoCurrencyFactory;
import com.globant.domain.user.accounts.BankAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
import com.globant.domain.util.OnlyReadCollection;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class PlaceSaleOrderUseCaseImpl implements PlaceSaleOrderUseCase{
    private final UserRepository userRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<String, BankAccount> bankRepository;
    private final ExchangeInstance exchangeInstance;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final Cache cache = Cache.getGlobalCacheInstance();
    private final CryptoCurrencyFactory cryptoCurrencyFactory;

    public PlaceSaleOrderUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository, 
            Repository<String, BankAccount> bankRepository, ExchangeInstance exchangeInstance, Repository<UserID, TransactionHistory> transactionHistoryRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.cryptoCurrencyFactory = new CryptoCurrencyFactory();
    }
    
    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException {
        verify(dto);
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        SalesOrder salesOrder = new SalesOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMinPrice());
        OnlyReadCollection<BuyOrder> buyOrders = cache.exchange.searchBuyOrder(salesOrder);
        if (buyOrders.isEmpty()){
            addToSaleBook(dto, salesOrder);
            return;
        }
        for (int i = 0; i<buyOrders.size(); i++){
            exchange(buyOrders.get(i), salesOrder);
        }
        generateTransaction(cache.currentUser, salesOrder, TransactionType.SELL);
        exchangeInstance.save(cache.exchange);
    }
    
    private void trade(BuyOrder buyOrder, SalesOrder salesOrder) throws DomainException{
        BigDecimal unitPrice = buyOrder.getMaxPrice().add(salesOrder.getMinPrice()).divide(new BigDecimal("2"));
        BigDecimal totalPrice = buyOrder.getRemainigAmount().multiply(unitPrice);
        if (buyOrder.getRemainigAmount().compareTo(salesOrder.getRemainigAmount()) > 0){
            totalPrice = salesOrder.getRemainigAmount().multiply(unitPrice);
            CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(buyOrder.getCryptoName(), salesOrder.getRemainigAmount());
            cache.currentUserWallet.reduceAmount(buyOrder.getCryptoName(), amount);
        }
        else{
            CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(buyOrder.getCryptoName(), buyOrder.getRemainigAmount());
            cache.currentUserWallet.reduceAmount(buyOrder.getCryptoName(), amount);
        }
        exchangeMoney(buyOrder, salesOrder, unitPrice, totalPrice);
    }
    
    private void exchangeMoney(BuyOrder buyOrder, SalesOrder salesOrder, BigDecimal unitPrice, BigDecimal totalPrice) throws DomainException{
        buyOrder.exchangeMoney(totalPrice);
        salesOrder.exchangeMoney(totalPrice);
        cache.exchange.updatePrice(salesOrder.getCryptoName(), unitPrice);
        cache.currentUserBankAccount.add(totalPrice);
        walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
        bankRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
    }
    
    private void verify(PlaceSaleOrderDTO dto) throws DomainException{
        if (dto.getAmount().signum() <= 0 || dto.getMinPrice().signum() <= 0){throw InvalidAmountException.invalidAmount();}
        if (cache.currentUserWallet.get(dto.getCryptoName()).getAmount().compareTo(dto.getAmount()) < 0)
        {throw InsufficientCurrencyException.insufficientAmount();}
    }
    
    private void addToSaleBook(PlaceSaleOrderDTO dto, SalesOrder salesOrder) throws DomainException{
        cache.exchange.addSaleOrder(salesOrder);
        exchangeInstance.save(cache.exchange);
        cache.currentUserWallet.reduceAmount(dto.getCryptoName(), salesOrder.getAmount());
        walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
    }
    
    private void exchange(BuyOrder buyOrder, SalesOrder salesOrder) throws DomainException{
        User payer = userRepository.get(buyOrder.getUserID());
        trade(buyOrder, salesOrder);
        BigDecimal remainigAmount = buyOrder.getRemainigAmount();
        buyOrder.exchange(salesOrder.getRemainigAmount());
        salesOrder.exchange(remainigAmount);
        if (buyOrder.isCompleted()){
            executeOrder(payer, buyOrder);
        }
    }
    
    private void executeOrder(User payer, BuyOrder buyOrder) throws DomainException{
        BankAccount payerBankAccount = bankRepository.get(payer.getNumberAccount().getNumberAccount());
        payerBankAccount.add(buyOrder.getExchangedMoney());
        bankRepository.save(payerBankAccount.getNumberAccount().getNumberAccount(), payerBankAccount);
        generateTransaction(payer, buyOrder, TransactionType.BUY);
        cache.exchange.removeBuyOrder(buyOrder);
    }
    
    private void generateTransaction(User user, Order order, TransactionType type) throws DomainException{
        TransactionHistory userHistory = new TransactionHistory(user.getUserID());
        if (transactionHistoryRepository.contain(user.getUserID())){
            userHistory = transactionHistoryRepository.get(user.getUserID());
        }
        userHistory.addTransaction(new Transaction(order.getExchangedMoney(), order.getAmount().getAmount(),order.getCryptoName(), user.getUserID(), type));
        transactionHistoryRepository.save(user.getUserID(), userHistory);
    }
}
