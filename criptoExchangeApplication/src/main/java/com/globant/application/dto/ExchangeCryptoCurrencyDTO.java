package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyDTO {
    private final CryptoCurrencyName cryptoName;
    private final CryptoCurrency amount;
    private UserID userID;

    public ExchangeCryptoCurrencyDTO(CryptoCurrencyName cryptoName, CryptoCurrency amount) {
        this.cryptoName = cryptoName;
        this.amount = amount;
    }

    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public CryptoCurrency getAmount(){return amount;}

    public UserID getUserID(){return userID;}
    
    public void setUserID(UserID id){userID = id;}
    
}
