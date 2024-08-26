package com.globant.domain.user;

import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public abstract class BankAccount {
    private final String numberAccount;
    protected BigDecimal money;
    
    public BankAccount(String numberAccount, BigDecimal money){
        this.numberAccount = numberAccount;
        this.money = money;
    }
    
    public String getNumberAccount(){return numberAccount;}
    
    public abstract void add(BigDecimal money);
    
    public abstract void reduce(BigDecimal money) throws InsufficientMoneyException;
}
