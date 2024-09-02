package com.globant.domain.exchange;

import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class SalesOrder extends Order{
    private final BigDecimal minPrice;
    
    public SalesOrder(CryptoCurrency amount, CryptoCurrencyName cryptoName, UserID userID, BigDecimal minPrice){
        super(amount, cryptoName, userID);
        this.minPrice = minPrice;
    }
    
    public BigDecimal getMinPrice(){return minPrice;}
}
