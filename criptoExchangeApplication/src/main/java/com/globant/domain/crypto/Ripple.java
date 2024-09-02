package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Ripple extends CryptoCurrency<Ripple>{
    
    public Ripple(){super();}
    
    public Ripple(BigDecimal amount) throws InvalidAmountException
    {super(amount);}

    @Override
    public Ripple add(Ripple amount) {
        try {return new Ripple(this.amount.add(amount.getAmount()));}
        catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }

    @Override
    public Ripple reduce(Ripple amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        try {return new Ripple(this.amount.subtract(amount.getAmount()));
        } catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }

    
}
