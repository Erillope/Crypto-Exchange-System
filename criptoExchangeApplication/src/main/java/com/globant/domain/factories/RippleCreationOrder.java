package com.globant.domain.factories;

import com.globant.domain.crypto.CryptoCurrency;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class RippleCreationOrder implements CryptoCurrencyCreationOrder{

    @Override
    public CryptoCurrency exectue(CryptoCurrencyFactory factory, BigDecimal amount) {
        return factory.createRiplle(amount);
    }
    
}
