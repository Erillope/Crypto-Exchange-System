
package models.exchange;

import java.math.BigDecimal;
import models.crypto.CryptoCurrency;
import models.crypto.CryptoCurrencyName;
import models.user.UserID;

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
    }
}
