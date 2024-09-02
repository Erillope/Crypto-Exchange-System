package com.globant.application.dto;

import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class PlaceSaleOrderDTO {
    private final CryptoCurrencyName cryptoName;
    private final BigDecimal amount;
    private final UserID userID;
    private final BigDecimal minPrice;

    public PlaceSaleOrderDTO(CryptoCurrencyName cryptoName, BigDecimal amount, BigDecimal minPrice, UserID userID) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.minPrice = minPrice;
        this.userID = userID;
    }
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public BigDecimal getAmount(){return amount;}

    public UserID getUserID(){return userID;}
    
    public BigDecimal getMinPrice(){return minPrice;}
}
