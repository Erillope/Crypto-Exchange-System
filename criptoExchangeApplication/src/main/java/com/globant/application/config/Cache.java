package com.globant.application.config;

import com.globant.domain.crypto.Wallet;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
import com.globant.domain.user.accounts.BankAccount;

/**
 *
 * @author erillope
 */
public abstract class Cache {
    private static Cache globalCacheInstance;
    
    public User currentUser;
    public Wallet currentUserWallet;
    public BankAccount currentUserBankAccount;
    public Exchange exchange;
    public Wallet exchangeWallet;
    public BankAccount exchangeBankAccount;
    public TransactionHistory currentUserTransactionHistory;
    
    public abstract void init(UserID userID) throws DomainException;
    
    public abstract void clear();
    
    public static void setGlobalCacheInstance(Cache cache){globalCacheInstance = cache;}
    
    public static Cache getGlobalCacheInstance(){return globalCacheInstance;}
}
