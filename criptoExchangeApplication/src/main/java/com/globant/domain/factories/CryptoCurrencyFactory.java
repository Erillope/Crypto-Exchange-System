package com.globant.domain.factories;

import com.globant.domain.crypto.Bitcoin;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.Ethereum;
import com.globant.domain.crypto.Ripple;
import com.globant.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class CryptoCurrencyFactory {
    private static final Map<CryptoCurrencyName, CryptoCurrencyCreationOrder> creationOrders;
    static{
        creationOrders = new HashMap<>();
        creationOrders.put(CryptoCurrencyName.BITCOIN, new BitcoinCreationOrder());
        creationOrders.put(CryptoCurrencyName.ETHEREUM, new EthereumCreationOrder());
        creationOrders.put(CryptoCurrencyName.RIPPLE, new RippleCreationOrder());
    }
    
    public Bitcoin createBitcoin(BigDecimal amount) throws InvalidAmountException{
        return new Bitcoin(amount);
    }
    
    public Ethereum createEthereum(BigDecimal amount) throws InvalidAmountException{
        return new Ethereum(amount);
    }
    
    public Ripple createRiplle(BigDecimal amount) throws InvalidAmountException{
        return new Ripple(amount);
    }
    
    public CryptoCurrency createCryptoCurrency(CryptoCurrencyName cryptoName, BigDecimal amount) throws InvalidAmountException{
        CryptoCurrencyCreationOrder creationOrder = creationOrders.get(cryptoName);
        return creationOrder.exectue(this, amount);
    }
}
