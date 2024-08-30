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
    private final UserID userID;

    public ExchangeCryptoCurrencyDTO(CryptoCurrencyName cryptoName, CryptoCurrency amount, UserID userID) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.userID = userID;
    }

    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public CryptoCurrency getAmount(){return amount;}

    public UserID getUserID(){return userID;}    
}
