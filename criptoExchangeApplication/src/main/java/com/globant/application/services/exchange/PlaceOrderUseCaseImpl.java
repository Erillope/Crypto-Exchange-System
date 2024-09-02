package com.globant.application.services.exchange;

import com.globant.application.config.ApplicationCache;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.domain.crypto.Bitcoin;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InsufficientMoneyException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.Order;
import com.globant.domain.exchange.SalesOrder;
import com.globant.domain.exchange.Transaction;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.exchange.TransactionType;
import com.globant.domain.factories.CryptoCurrencyFactory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
import com.globant.domain.util.OnlyReadCollection;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class PlaceOrderUseCaseImpl implements PlaceOrderUseCase{
    private final UserRepository userRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<String, BankAccount> bankRepository;
    private final ExchangeInstance exchangeInstance;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final BankTransactionExecuter transactionExecuter;
    private final ApplicationCache cache = ApplicationCache.getInstance();
    private final CryptoCurrencyFactory cryptoCurrencyFactory;

    public PlaceOrderUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository, Repository<String, BankAccount> bankRepository, ExchangeInstance exchangeInstance, Repository<UserID, TransactionHistory> transactionHistoryRepository, BankTransactionExecuter transactionExecuter) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionExecuter = transactionExecuter;
        this.cryptoCurrencyFactory = new CryptoCurrencyFactory();
    }
    
    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException {
        if (dto.getAmount().signum() <= 0 || dto.getMinPrice().signum() <= 0){throw InvalidAmountException.invalidAmount();}
        if (cache.currentUserWallet.get(dto.getCryptoName()).getAmount().compareTo(dto.getAmount()) < 0)
        {throw InsufficientCurrencyException.insufficientAmount();}
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
      
        SalesOrder salesOrder = new SalesOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMinPrice());
        OnlyReadCollection<BuyOrder> buyOrders = cache.exchange.searchBuyOrder(salesOrder);
        if (buyOrders.isEmpty()){
            cache.exchange.salesOrderBook.add(salesOrder);
            exchangeInstance.save(cache.exchange);
            cache.currentUserWallet.reduceAmount(dto.getCryptoName(), amount);
            walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
            return;
        }
        for (int i = 0; i<buyOrders.size(); i++){
            User payer = userRepository.get(buyOrders.get(i).getUserID());
            tradeS(buyOrders.get(i), salesOrder);
            BigDecimal remainigAmount = buyOrders.get(i).getRemainigAmount();
            buyOrders.get(i).exchange(salesOrder.getRemainigAmount());
            salesOrder.exchange(remainigAmount);
            if (buyOrders.get(i).isCompleted()){
                BankAccount payerBankAccount = bankRepository.get(payer.getNumberAccount().getNumberAccount());
                payerBankAccount.add(buyOrders.get(i).getExchangedMoney());
                bankRepository.save(payerBankAccount.getNumberAccount().getNumberAccount(), payerBankAccount);
                generateTransaction(payer, buyOrders.get(i), TransactionType.BUY);
                cache.exchange.buyOrderBook.remove(buyOrders.get(i));
            }
        }
        generateTransaction(cache.currentUser, salesOrder, TransactionType.SELL);
        exchangeInstance.save(cache.exchange);
    }
    
    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException {
        if (dto.getAmount().signum() <= 0 || dto.getMaxPrice().signum() <= 0){throw InvalidAmountException.invalidAmount();}
        BigDecimal maxTotalPrice = dto.getAmount().multiply(dto.getMaxPrice());
        if (cache.currentUserBankAccount.getMoney().compareTo(maxTotalPrice) < 0){throw InsufficientMoneyException.insufficientMoney();}
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        BuyOrder buyOrder = new BuyOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMaxPrice()); 
        OnlyReadCollection<SalesOrder> salesOrder = cache.exchange.searchSalesOrder(buyOrder);
        if (salesOrder.isEmpty()){
            cache.exchange.buyOrderBook.add(buyOrder);
            exchangeInstance.save(cache.exchange);
            cache.currentUserBankAccount.reduce(maxTotalPrice);
            bankRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
            return;
        }
        for (int i = 0; i<salesOrder.size(); i++){
            User beneficiary = userRepository.get(salesOrder.get(i).getUserID());            
            tradeB(salesOrder.get(i), buyOrder);
            BigDecimal remainigAmount = salesOrder.get(i).getRemainigAmount();
            salesOrder.get(i).exchange(buyOrder.getRemainigAmount());
            buyOrder.exchange(remainigAmount);
            if (salesOrder.get(i).isCompleted()){
                BankAccount beneficiaryBankAccount = bankRepository.get(beneficiary.getNumberAccount().getNumberAccount());
                beneficiaryBankAccount.add(salesOrder.get(i).getExchangedMoney());
                bankRepository.save(beneficiaryBankAccount.getNumberAccount().getNumberAccount(), beneficiaryBankAccount);
                generateTransaction(beneficiary, salesOrder.get(i), TransactionType.SELL);
                cache.exchange.salesOrderBook.remove(salesOrder.get(i));
            }
        }
        generateTransaction(cache.currentUser, buyOrder, TransactionType.BUY);
        exchangeInstance.save(cache.exchange);
    }
    
    private void tradeB(SalesOrder salesOrder, BuyOrder buyOrder) throws DomainException{
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
    
    private void tradeS(BuyOrder buyOrder, SalesOrder salesOrder) throws DomainException{
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
        buyOrder.exchangeMoney(totalPrice);
        salesOrder.exchangeMoney(totalPrice);
        cache.exchange.updatePrice(salesOrder.getCryptoName(), unitPrice);
        cache.currentUserBankAccount.add(totalPrice);
        walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
        bankRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
    }
    
    private void generateTransaction(User user, Order order, TransactionType type) throws DomainException{
        TransactionHistory userHistory = null;
        if (transactionHistoryRepository.contain(user.getUserID())){
            userHistory = transactionHistoryRepository.get(user.getUserID());
        }
        else{userHistory = new TransactionHistory(user.getUserID());}
       
        userHistory.addTransaction(new Transaction(order.getExchangedMoney(), order.getAmount().getAmount(),order.getCryptoName(), user.getUserID(), type));
        transactionHistoryRepository.save(user.getUserID(), userHistory);
    }
}
