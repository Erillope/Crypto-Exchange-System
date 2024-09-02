package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Bitcoin extends CryptoCurrency<Bitcoin>{
    
    public Bitcoin(){super();}
    
    public Bitcoin(BigDecimal amount) throws InvalidAmountException
    {super(amount);}

    @Override
    public Bitcoin add(Bitcoin amount) {
        try {return new Bitcoin(this.amount.add(amount.getAmount()));}
        catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }

    @Override
    public Bitcoin reduce(Bitcoin amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        try {return new Bitcoin(this.amount.subtract(amount.getAmount()));
        } catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }
    
}
