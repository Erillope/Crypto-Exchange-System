package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyDTO {
    private final CryptoCurrencyName cryptoName;
    private final BigDecimal amount;
    private final UserID userID;

    public ExchangeCryptoCurrencyDTO(CryptoCurrencyName cryptoName, BigDecimal amount, UserID userID) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.userID = userID;
    }

    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public BigDecimal getAmount(){return amount;}

    public UserID getUserID(){return userID;}    
}
