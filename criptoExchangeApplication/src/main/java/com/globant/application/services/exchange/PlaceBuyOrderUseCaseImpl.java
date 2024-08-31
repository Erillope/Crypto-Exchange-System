package com.globant.application.services.exchange;

import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.NotFoundSalesOrderException;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.exchange.SalesOrder;
import com.globant.domain.exchange.Transaction;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.exchange.TransactionType;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
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
    private final BankTransactionExecuter transactionExecuter;

    public PlaceBuyOrderUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository, 
            Repository<String, BankAccount> bankRepository, ExchangeInstance exchangeInstance, 
            Repository<UserID, TransactionHistory> transactionHistoryRepository, BankTransactionExecuter transactionExecute) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionExecuter = transactionExecute;
    }
    
    
    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException{
        User user = userRepository.get(dto.getUserID());
        BuyOrder buyOrder = user.generateBuyOrder(dto.getAmount(), dto.getCryptoName(), dto.getMaxPrice());
        Exchange exchange = exchangeInstance.get();
        OnlyReadCollection<SalesOrder> salesOrders = exchange.searchSalesOrder(buyOrder);
        if (salesOrders.isEmpty()){throw NotFoundSalesOrderException.notFound();}
        for (int i = 0; i < salesOrders.size(); i++){
            trade(user, salesOrders.get(i));
        }
    }
    
    private void trade(User payer, SalesOrder order) throws DomainException{
        User beneficiary = userRepository.get(order.getUserID());
        Exchange exchange = exchangeInstance.get();
        Wallet payerWallet = walletRepository.get(payer.getWalletID());
        Wallet beneficiaryWallet = walletRepository.get(beneficiary.getWalletID());
        BankAccount payerBankAccount = bankRepository.get(payer.getNumberAccount().getNumberAccount());
        BankAccount beneficiaryBankAccount = bankRepository.get(beneficiary.getNumberAccount().getNumberAccount());
        payerWallet.reduceAmount(order.getCryptoName(), order.getAmount());
        beneficiaryWallet.addAmount(order.getCryptoName(), order.getAmount());
        BigDecimal totalPrice = order.getAmount().getAmount().multiply(exchange.getPrice(order.getCryptoName()));
        transactionExecuter.execute(payerBankAccount, beneficiaryBankAccount, totalPrice);
        generateTransaction(payer, beneficiary, order);
    }
    
    private void generateTransaction(User payer, User beneficiary, SalesOrder order) throws DomainException{
        TransactionHistory payerHistory = transactionHistoryRepository.get(payer.getUserID());
        TransactionHistory beneficiaryHistory = transactionHistoryRepository.get(beneficiary.getUserID());
        //payerHistory.addTransaction(new Transaction(order, TransactionType.BUY));
        //beneficiaryHistory.addTransaction(new Transaction(order, TransactionType.SELL));
        transactionHistoryRepository.save(payer.getUserID(), payerHistory);
        transactionHistoryRepository.save(beneficiary.getUserID(), beneficiaryHistory);
    }
    
}
