package com.globant.domain.factories;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class RippleCreationOrder implements CryptoCurrencyCreationOrder{

    @Override
    public CryptoCurrency exectue(CryptoCurrencyFactory factory, BigDecimal amount) throws InvalidAmountException {
        return factory.createRiplle(amount);
    }
    
}
