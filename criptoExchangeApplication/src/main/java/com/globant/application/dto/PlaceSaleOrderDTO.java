package application.dto;

import java.math.BigDecimal;
import models.crypto.CryptoCurrencyName;

/**
 *
 * @author erillope
 */
public class PlaceSaleOrderDTO {
    private final CryptoCurrencyName cryptoName;
    private final BigDecimal amount;
    private final String UserID;
    private final BigDecimal minPrice;

    public PlaceSaleOrderDTO(CryptoCurrencyName cryptoName, BigDecimal amount, String UserID, BigDecimal minPrice) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.UserID = UserID;
        this.minPrice = minPrice;
    }
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public BigDecimal getAmount(){return amount;}

    public String getUserID(){return UserID;}
    
    public BigDecimal getMinPrice(){return minPrice;}
}
