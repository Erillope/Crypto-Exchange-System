package com.globant.application.config;

import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.SerExchangeInstance;
import com.globant.application.repositories.TransactionHistorySerRepository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.application.repositories.WalletSerRepository;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class ApplicationCache implements Cache{
    private static ApplicationCache instance;
    public User currentUser;
    public Wallet currentUserWallet;
    public BankAccount currentUserBankAccount;
    public Exchange exchange;
    public Wallet exchangeWallet;
    public BankAccount exchangeBankAccount;
    public TransactionHistory currentUserTransactionHistory;
    
    private final UserRepository userRepository;
    private final Repository<String, BankAccount> bankAccountRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final ExchangeInstance exchangeInstance;

    private ApplicationCache() {
        userRepository = UserSerRepository.getInstance();
        bankAccountRepository = BankAccountSerRepository.getInstance();
        transactionHistoryRepository = TransactionHistorySerRepository.getInstance();
        walletRepository = WalletSerRepository.getInstance();
        exchangeInstance = SerExchangeInstance.getInstance();
    }
    
    @Override
    public void init(UserID userID) throws DomainException{
        currentUser = userRepository.get(userID);
        currentUserWallet = walletRepository.get(currentUser.getWalletID());
        currentUserBankAccount = bankAccountRepository.get(currentUser.getNumberAccount().getNumberAccount());
        exchange = exchangeInstance.get();
        exchangeWallet = walletRepository.get(exchange.getWalletID());
        exchangeBankAccount = bankAccountRepository.get(exchange.getNumberAccount().getNumberAccount());
        if (transactionHistoryRepository.contain(userID)){
            currentUserTransactionHistory = transactionHistoryRepository.get(userID);
        }
        else{currentUserTransactionHistory = new TransactionHistory(userID);}
    }
        
    @Override
    public void clear(){
        currentUser = null;
        currentUserWallet = null;
        currentUserBankAccount = null;
        exchange = null;
        exchangeWallet =null;
        exchangeBankAccount = null;
        currentUserTransactionHistory = null;
    }
    
    public static ApplicationCache getInstance(){
        if (instance == null){instance = new ApplicationCache();}
        return instance;
    }
}
