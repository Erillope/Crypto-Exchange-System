package com.globant.application.services.exchange;

import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.NotFoundBuyOrderException;
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
public class PlaceSaleOrderUseCaseImpl implements PlaceSaleOrderUseCase{
    private final UserRepository userRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<String, BankAccount> bankRepository;
    private final ExchangeInstance exchangeInstance;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final BankTransactionExecuter transactionExecuter;

    public PlaceSaleOrderUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository, Repository<String, BankAccount> bankRepository, ExchangeInstance exchangeInstance, Repository<UserID, TransactionHistory> transactionHistoryRepository, BankTransactionExecuter transactionExecuter) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionExecuter = transactionExecuter;
    }
    
    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException {
        User user = userRepository.get(dto.getUserID());
        SalesOrder salesOrder = user.generateSalesOrder(dto.getAmount(), dto.getCryptoName(), dto.getMinPrice());
        Exchange exchange = exchangeInstance.get();
        OnlyReadCollection<BuyOrder> buyOrders = exchange.searchBuyOrder(salesOrder);
        if (buyOrders.isEmpty()){throw NotFoundBuyOrderException.notFound();}
        for (int i = 0; i < buyOrders.size(); i++){
            trade(user, buyOrders.get(i));
        }
    }
    
    private void trade(User beneficiary, BuyOrder order) throws DomainException{
        User payer = userRepository.get(order.getUserID());
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
    
    private void generateTransaction(User payer, User beneficiary, BuyOrder order) throws DomainException{
        TransactionHistory payerHistory = transactionHistoryRepository.get(payer.getUserID());
        TransactionHistory beneficiaryHistory = transactionHistoryRepository.get(beneficiary.getUserID());
        //payerHistory.addTransaction(new Transaction(order, TransactionType.BUY));
        //beneficiaryHistory.addTransaction(new Transaction(order, TransactionType.SELL));
        transactionHistoryRepository.save(payer.getUserID(), payerHistory);
        transactionHistoryRepository.save(beneficiary.getUserID(), beneficiaryHistory);
    }
    
}
