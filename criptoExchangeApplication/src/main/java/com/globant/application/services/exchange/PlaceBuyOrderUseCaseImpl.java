package com.globant.application.services.exchange;

import com.globant.application.config.Cache;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InsufficientMoneyException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.Order;
import com.globant.domain.exchange.SalesOrder;
import com.globant.domain.exchange.Transaction;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.exchange.TransactionType;
import com.globant.domain.factories.creationorders.CryptoCurrencyFactory;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
import com.globant.domain.user.accounts.BankAccount;
import com.globant.domain.util.OnlyReadCollection;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class PlaceBuyOrderUseCaseImpl implements PlaceBuyOrderUseCase{
    private final UserRepository userRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<String, BankAccount> bankRepository;
    private final ExchangeInstance exchangeInstance;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final Cache cache = Cache.getGlobalCacheInstance();
    private final CryptoCurrencyFactory cryptoCurrencyFactory;

    public PlaceBuyOrderUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository, 
            Repository<String, BankAccount> bankRepository, ExchangeInstance exchangeInstance, Repository<UserID, TransactionHistory> transactionHistoryRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.cryptoCurrencyFactory = new CryptoCurrencyFactory();
    }
    
    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException {
        BigDecimal maxTotalPrice = dto.getAmount().multiply(dto.getMaxPrice());
        verify(dto, maxTotalPrice);
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        BuyOrder buyOrder = new BuyOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMaxPrice()); 
        OnlyReadCollection<SalesOrder> salesOrder = cache.exchange.searchSalesOrder(buyOrder);
        if (salesOrder.isEmpty()){
            addToBuyBook(buyOrder, maxTotalPrice);
            return;
        }
        for (int i = 0; i<salesOrder.size(); i++){
            exchange(salesOrder.get(i), buyOrder);
            }
        generateTransaction(cache.currentUser, buyOrder, TransactionType.BUY);
        exchangeInstance.save(cache.exchange);
    }
    
    private void trade(SalesOrder salesOrder, BuyOrder buyOrder) throws DomainException{
        BigDecimal unitPrice = buyOrder.getMaxPrice().add(salesOrder.getMinPrice()).divide(new BigDecimal("2"));
        
        BigDecimal totalPrice = salesOrder.getRemainigAmount().multiply(unitPrice);
        if (salesOrder.getRemainigAmount().compareTo(buyOrder.getRemainigAmount()) > 0){
            totalPrice = buyOrder.getRemainigAmount().multiply(unitPrice);
            CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(salesOrder.getCryptoName(), buyOrder.getRemainigAmount());
            cache.currentUserWallet.addAmount(salesOrder.getCryptoName(), amount);
        }
        else{
            CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(salesOrder.getCryptoName(), salesOrder.getRemainigAmount());
            cache.currentUserWallet.addAmount(salesOrder.getCryptoName(), amount);
        }
        buyOrder.exchangeMoney(totalPrice);
        salesOrder.exchangeMoney(totalPrice);
        cache.exchange.updatePrice(salesOrder.getCryptoName(), unitPrice);
        cache.currentUserBankAccount.reduce(totalPrice);
        walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
        bankRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
    }
    
    private void verify(PlaceBuyOrderDTO dto, BigDecimal maxTotalPrice) throws DomainException{
        if (dto.getAmount().signum() <= 0 || dto.getMaxPrice().signum() <= 0)
        {throw InvalidAmountException.invalidAmount();}
        if (cache.currentUserBankAccount.getMoney().compareTo(maxTotalPrice) < 0)
        {throw InsufficientMoneyException.insufficientMoney();}
    }
    
    private void addToBuyBook(BuyOrder buyOrder, BigDecimal maxTotalPrice) throws DomainException{
        cache.exchange.addBuyOrder(buyOrder);
        exchangeInstance.save(cache.exchange);
        cache.currentUserBankAccount.reduce(maxTotalPrice);
        bankRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
    }
    
    private void exchange(SalesOrder salesOrder, BuyOrder buyOrder) throws DomainException{
        User beneficiary = userRepository.get(salesOrder.getUserID());            
        trade(salesOrder, buyOrder);
        BigDecimal remainigAmount = salesOrder.getRemainigAmount();
        salesOrder.exchange(buyOrder.getRemainigAmount());
        buyOrder.exchange(remainigAmount);
        if (salesOrder.isCompleted()){
            executeOrder(beneficiary, salesOrder);
        }
    }
    
    private void executeOrder(User beneficiary, SalesOrder salesOrder)throws DomainException{
        BankAccount beneficiaryBankAccount = bankRepository.get(beneficiary.getNumberAccount().getNumberAccount());
        beneficiaryBankAccount.add(salesOrder.getExchangedMoney());
        bankRepository.save(beneficiaryBankAccount.getNumberAccount().getNumberAccount(), beneficiaryBankAccount);
        generateTransaction(beneficiary, salesOrder, TransactionType.SELL);
        cache.exchange.removeSaleOrder(salesOrder);
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