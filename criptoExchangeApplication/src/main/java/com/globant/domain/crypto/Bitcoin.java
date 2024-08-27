package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Bitcoin extends CryptoCurrency<Bitcoin>{
    
    public Bitcoin(){super();}
    
    public Bitcoin(BigDecimal amount){
        this.amount = amount;
    }

    @Override
    public Bitcoin add(Bitcoin amount) {
        return new Bitcoin(this.amount.add(amount.getAmount()));
    }

    @Override
    public Bitcoin reduce(Bitcoin amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        return new Bitcoin(this.amount.subtract(amount.getAmount()));
    }
    
}
