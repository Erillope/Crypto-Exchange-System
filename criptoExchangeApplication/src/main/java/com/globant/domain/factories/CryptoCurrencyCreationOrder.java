package com.globant.domain.factories;

import com.globant.domain.crypto.CryptoCurrency;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public interface CryptoCurrencyCreationOrder {
    public CryptoCurrency exectue(CryptoCurrencyFactory factory, BigDecimal amount);
}
