package com.globant.domain.factories;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public interface CryptoCurrencyCreationOrder {
    public CryptoCurrency exectue(CryptoCurrencyFactory factory, BigDecimal amount) throws InvalidAmountException;
}
