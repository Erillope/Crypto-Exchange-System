package com.globant.application.services.exchange;

import com.globant.application.config.ApplicationCache;
import com.globant.application.config.Cache;
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
import com.globant.domain.exceptions.NotFoundBuyOrderException;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.Exchange;
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
        if (cache.currentUserWallet.get(dto.getCryptoName()).getAmount().compareTo(dto.getAmount()) < 0)
        {throw InsufficientCurrencyException.insufficientAmount();}
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        SalesOrder salesOrder = new SalesOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMinPrice());
        OnlyReadCollection<BuyOrder> buyOrders = cache.exchange.searchBuyOrder(salesOrder);
        if (buyOrders.isEmpty()){
            cache.exchange.salesOrderBook.add(salesOrder);
            //exchangeInstance.save(cache.exchange);
            return;
        }
        for (int i = 0; i<buyOrders.size(); i++){
            User payer = userRepository.get(buyOrders.get(i).getUserID());
            BigDecimal unitPrice = salesOrder.getMinPrice().add(buyOrders.get(i).getMaxPrice()).divide(new BigDecimal("2"));
            trade( cache.currentUser, payer, buyOrders.get(i), unitPrice, salesOrder.getRemainigAmount());
            BigDecimal remainigAmount = buyOrders.get(i).getRemainigAmount();
            buyOrders.get(i).exchange(salesOrder.getRemainigAmount());
            salesOrder.exchange(remainigAmount);
            if (buyOrders.get(i).isCompleted()){
                generateTransaction(payer, buyOrders.get(i), TransactionType.BUY);
                cache.exchange.buyOrderBook.remove(buyOrders.get(i));
            }
        }
        generateTransaction(cache.currentUser, salesOrder, TransactionType.SELL);
        //exchangeInstance.save(cache.exchange);
    }
    
    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException {
        BigDecimal maxTotalPrice = dto.getAmount().multiply(dto.getMaxPrice());
        if (cache.currentUserBankAccount.getMoney().compareTo(maxTotalPrice) < 0){throw InsufficientMoneyException.insufficientMoney();}
        CryptoCurrency amount = cryptoCurrencyFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        BuyOrder buyOrder = new BuyOrder(amount, dto.getCryptoName(), dto.getUserID(), dto.getMaxPrice()); 
        OnlyReadCollection<SalesOrder> salesOrder = cache.exchange.searchSalesOrder(buyOrder);
        if (salesOrder.isEmpty()){
            cache.exchange.buyOrderBook.add(buyOrder);
            //exchangeInstance.save(cache.exchange);
            return;
        }
        for (int i = 0; i<salesOrder.size(); i++){
            User beneficiary = userRepository.get(salesOrder.get(i).getUserID());
            BigDecimal unitPrice = buyOrder.getMaxPrice().add(salesOrder.get(i).getMinPrice()).divide(new BigDecimal("2"));
            trade(beneficiary, cache.currentUser, salesOrder.get(i), unitPrice, buyOrder.getRemainigAmount());
            BigDecimal remainigAmount = salesOrder.get(i).getRemainigAmount();
            salesOrder.get(i).exchange(buyOrder.getRemainigAmount());
            buyOrder.exchange(remainigAmount);
            if (salesOrder.get(i).isCompleted()){
                generateTransaction(beneficiary, salesOrder.get(i), TransactionType.SELL);
                cache.exchange.salesOrderBook.remove(salesOrder.get(i));
            }
        }
        generateTransaction(cache.currentUser, buyOrder, TransactionType.BUY);
        //exchangeInstance.save(cache.exchange);
    }
    
    private void trade(User beneficiary, User payer, Order order, BigDecimal unitPrice, BigDecimal maxTake) throws DomainException{
        Wallet beneficiaryWallet = walletRepository.get(beneficiary.getWalletID());
        Wallet payerWallet = walletRepository.get(payer.getWalletID());
        BankAccount beneficiaryBankAccount = bankRepository.get(beneficiary.getNumberAccount().getNumberAccount());
        BankAccount payerBankAccount = bankRepository.get(payer.getNumberAccount().getNumberAccount());
        payerWallet.addAmount(CryptoCurrencyName.BITCOIN, new Bitcoin(new BigDecimal("20")));//borrar
        payerWallet.reduceAmount(order.getCryptoName(), order.getAmount());
        beneficiaryWallet.addAmount(order.getCryptoName(), order.getAmount());
        BigDecimal totalPrice = order.getAmount().getAmount().multiply(unitPrice);
        if (order.getAmount().getAmount().compareTo(maxTake) > 0){totalPrice = maxTake.multiply(unitPrice);}
        transactionExecuter.execute(payerBankAccount, beneficiaryBankAccount, totalPrice);
        //walletRepository.save(payerWallet.getID(), payerWallet);
        //walletRepository.save(beneficiaryWallet.getID(), beneficiaryWallet);
        //bankRepository.save(payerBankAccount.getNumberAccount().getNumberAccount(), payerBankAccount);
        //bankRepository.save(beneficiaryBankAccount.getNumberAccount().getNumberAccount(), beneficiaryBankAccount);
    }
    
    private void generateTransaction(User user, Order order, TransactionType type) throws DomainException{
        TransactionHistory userHistory = null;
        if (transactionHistoryRepository.contain(user.getUserID())){
            userHistory = transactionHistoryRepository.get(user.getUserID());
        }
        else{userHistory = new TransactionHistory(user.getUserID());}
       
        userHistory.addTransaction(new Transaction(order.getAmount().getAmount(), order.getCryptoName(), order.getUserID(), type));
        //transactionHistoryRepository.save(user.getUserID(), userHistory);
    }
}
