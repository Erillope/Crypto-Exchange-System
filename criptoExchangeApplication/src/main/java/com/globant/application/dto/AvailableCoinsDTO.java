package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.util.OnlyReadMap;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class AvailableCoinsDTO {
    private final OnlyReadMap<CryptoCurrencyName, CryptoCurrency> cryptoCurrency;
    private final OnlyReadMap<CryptoCurrencyName, BigDecimal> prices;
    
    public AvailableCoinsDTO(OnlyReadMap<CryptoCurrencyName, CryptoCurrency> cryptoCurrency, OnlyReadMap<CryptoCurrencyName, BigDecimal> prices){
        this.cryptoCurrency = cryptoCurrency;
        this.prices = prices;
    }

    public OnlyReadMap<CryptoCurrencyName, CryptoCurrency> getCryptoCurrency(){return cryptoCurrency;}
    
    public OnlyReadMap<CryptoCurrencyName, BigDecimal> getPrices(){return prices;}
    
}
