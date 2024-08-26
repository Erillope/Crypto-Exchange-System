package application.dto;

import java.math.BigDecimal;
import models.crypto.CryptoCurrencyName;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyDTO {
    private final CryptoCurrencyName cryptoName;
    private final BigDecimal amount;
    private final String UserID;

    public ExchangeCryptoCurrencyDTO(CryptoCurrencyName cryptoName, BigDecimal amount, String UserID) {
        this.cryptoName = cryptoName;
        this.amount = amount;
        this.UserID = UserID;
    }

    public CryptoCurrencyName getCryptoName(){return cryptoName;}

    public BigDecimal getAmount(){return amount;}

    public String getUserID(){return UserID;}
    
}
