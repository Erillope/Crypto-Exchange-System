package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Ethereum extends CryptoCurrency<Ethereum>{
    
    public Ethereum(){super();}
    
    public Ethereum(BigDecimal amount) throws InvalidAmountException
    {super(amount);}

    @Override
    public Ethereum add(Ethereum amount) {
        try {return new Ethereum(this.amount.add(amount.getAmount()));}
        catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }

    @Override
    public Ethereum reduce(Ethereum amount) throws InsufficientCurrencyException {
        if (this.amount.compareTo(amount.getAmount()) < 0){throw InsufficientCurrencyException.insufficientAmount();}
        try {return new Ethereum(this.amount.subtract(amount.getAmount()));
        } catch (InvalidAmountException ex) {ex.printStackTrace();}
        return null;
    }
}
