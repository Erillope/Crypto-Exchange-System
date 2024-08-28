package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrency;
import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class PlaceBuyOrderDTO {
    private final CryptoCurrencyName cryptoName;
    private final CryptoCurrency amount;
    private UserID userID;
    private final BigDecimal maxPrice;

    public PlaceBuyOrderDTO(CryptoCurrencyName cryptoName, CryptoCurrency amount, BigDecimal maxPrice) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.maxPrice = maxPrice;
    }
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public CryptoCurrency getAmount(){return amount;}

    public UserID getUserID(){return userID;}
    
    public BigDecimal getMaxPrice(){return maxPrice;}
    
    public void setUserID(UserID id){userID = id;}
    
}
