package com.globant.application.dto;

import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrencyName;

/**
 *
 * @author erillope
 */
public class PlaceBuyOrderDTO {
    private final CryptoCurrencyName cryptoName;
    private final BigDecimal amount;
    private final String UserID;
    private final BigDecimal maxPrice;

    public PlaceBuyOrderDTO(CryptoCurrencyName cryptoName, BigDecimal amount, String UserID, BigDecimal maxPrice) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.UserID = UserID;
        this.maxPrice = maxPrice;
    }
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public BigDecimal getAmount(){return amount;}

    public String getUserID(){return UserID;}
    
    public BigDecimal getMaxPrice(){return maxPrice;}
    
}
