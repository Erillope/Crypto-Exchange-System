package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Ripple extends CryptoCurrency<Ripple>{
    
    public Ripple(){super();}
    
    public Ripple(BigDecimal amount){
        this.amount = amount;
    }

    @Override
    public Ripple add(Ripple amount) {
        return new Ripple(this.amount.add(amount.getAmount()));
    }

    @Override
    public Ripple reduce(Ripple amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        return new Ripple(this.amount.subtract(amount.getAmount()));
    }
    
}
