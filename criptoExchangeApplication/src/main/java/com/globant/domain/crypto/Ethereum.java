package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Ethereum extends CryptoCurrency<Ethereum>{
    
    public Ethereum(){super();}
    
    public Ethereum(BigDecimal amount){
        this.amount = amount;
    }

    @Override
    public Ethereum add(Ethereum amount) {
        return new Ethereum(this.amount.add(amount.getAmount()));
    }

    @Override
    public Ethereum reduce(Ethereum amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        return new Ethereum(this.amount.subtract(amount.getAmount()));
    }
    
}
