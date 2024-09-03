package com.globant.domain.factories.creationorders;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class EthereumCreationOrder implements CryptoCurrencyCreationOrder{

    @Override
    public CryptoCurrency exectue(CryptoCurrencyFactory factory, BigDecimal amount) throws InvalidAmountException {
        return factory.createEthereum(amount);
    }
    
}
