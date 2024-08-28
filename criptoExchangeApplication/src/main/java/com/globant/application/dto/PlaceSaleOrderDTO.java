package com.globant.application.dto;

import com.globant.domain.crypto.CryptoCurrency;
import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class PlaceSaleOrderDTO {
    private final CryptoCurrencyName cryptoName;
    private final CryptoCurrency amount;
    private UserID userID;
    private final BigDecimal minPrice;

    public PlaceSaleOrderDTO(CryptoCurrencyName cryptoName, CryptoCurrency amount, BigDecimal minPrice) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.minPrice = minPrice;
    }
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public CryptoCurrency getAmount(){return amount;}

    public UserID getUserID(){return userID;}
    
    public BigDecimal getMinPrice(){return minPrice;}
    
    public void setUserID(UserID id){userID = id;}
}
