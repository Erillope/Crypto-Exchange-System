package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InvalidAmountException;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 * @param <T>
 */
public abstract class CryptoCurrency<T extends CryptoCurrency<T>> implements Serializable{
    protected BigDecimal amount;
    
    public CryptoCurrency(){
        amount = BigDecimal.ZERO;
    }
    
    public CryptoCurrency(BigDecimal amount) throws InvalidAmountException{
        if (amount.signum() < 0){throw InvalidAmountException.invalidAmount();}
        this.amount = amount;
    }
    
    public BigDecimal getAmount(){return amount;}
    
    public abstract T add(T amount);
    
    public abstract T reduce(T amount) throws InsufficientCurrencyException;

    @Override
    public String toString() {
        return amount.toString();
    }
}
