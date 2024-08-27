package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 * @param <T>
 */
public abstract class CryptoCurrency<T extends CryptoCurrency<T>> {
    public BigDecimal INITIAL_PRICE;
    private final BigDecimal amount;
    
    public CryptoCurrency(){
        amount = BigDecimal.ZERO;
    }
    
    public BigDecimal getAmount(){return amount;}
    
    public abstract T add(T amount);
    
    public abstract T reduce(T amount) throws InsufficientCurrencyException;
}
