
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
public class BuyOrder extends Order{
    private final BigDecimal maxPrice;
    
    public BuyOrder(CryptoCurrency amount, CryptoCurrencyName cryptoName, UserID userID, BigDecimal maxPrice){
        super(amount, cryptoName, userID);
        this.maxPrice = maxPrice;
    }
    
    public BigDecimal getMaxPrice(){return maxPrice;}

    @Override
    protected void verifyAmount() throws InvalidAmountException {
         Exchange exchange = Exchange.getInstance();
         BigDecimal price = exchange.getPrice(getCryptoName());
         BigDecimal totalPrice = price.multiply(getAmount().getAmount());
         if (totalPrice.compareTo(maxPrice) > 0){throw InvalidAmountException.invalidAmount();}
    }
}
