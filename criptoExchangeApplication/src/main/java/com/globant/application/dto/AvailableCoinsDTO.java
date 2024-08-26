package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.util.OnlyReadCollection;

/**
 *
 * @author erillope
 */
public class AvailableCoinsDTO {
    private final OnlyReadCollection<CryptoCurrencyName> cryptoCurrencyNames;
    
    public AvailableCoinsDTO(OnlyReadCollection<CryptoCurrencyName> cryptoCurrencyNames){
        this.cryptoCurrencyNames = cryptoCurrencyNames;
    }

    public OnlyReadCollection<CryptoCurrencyName> getCryptoCurrencyNames(){return cryptoCurrencyNames;}
}
