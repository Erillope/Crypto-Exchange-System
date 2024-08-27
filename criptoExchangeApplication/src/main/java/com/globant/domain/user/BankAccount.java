package com.globant.domain.user;

import com.globant.domain.exceptions.InsufficientMoneyException;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public abstract class BankAccount implements Serializable{
    private final NumberAccount numberAccount;
    protected BigDecimal money;
    
    public BankAccount(NumberAccount numberAccount, BigDecimal money){
        this.numberAccount = numberAccount;
        this.money = money;
    }
    
    public NumberAccount getNumberAccount(){return numberAccount;}
    
    public BigDecimal getMoney(){return money;}
    
    public abstract void add(BigDecimal money);
    
    public abstract void reduce(BigDecimal money) throws InsufficientMoneyException;
}
