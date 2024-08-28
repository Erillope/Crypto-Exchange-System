package com.globant.domain.exchange;

import com.globant.domain.exceptions.InvalidAmountException;
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
    

    @Override
    protected void verifyAmount() throws InvalidAmountException {
        Exchange exchange = Exchange.getInstance();
        BigDecimal price = exchange.getPrice(getCryptoName());
        BigDecimal totalPrice = price.multiply(getAmount().getAmount());
        if (totalPrice.compareTo(minPrice) < 0){throw InvalidAmountException.invalidAmount();}
    }


}
