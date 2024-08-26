package models.exchange;

import java.math.BigDecimal;
import models.crypto.CryptoCurrency;
import models.crypto.CryptoCurrencyName;
import models.user.UserID;

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
        //TODO
    }


}
