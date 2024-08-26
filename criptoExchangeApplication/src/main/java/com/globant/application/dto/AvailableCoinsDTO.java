package application.dto;

import models.crypto.CryptoCurrencyName;
import models.util.OnlyReadCollection;

/**
 *
 * @author erillope
 */
public class AvailableCoinsDTO {
    private final OnlyReadCollection<CryptoCurrencyName> cryptoCurrencyNames;
    
    public AvailableCoinsDTO(OnlyReadCollection<CryptoCurrencyName> cryptoCurrencyNames){
        this.cryptoCurrencyNames = cryptoCurrencyNames;
    }

    public OnlyReadCollection<CryptoCurrencyName> getCryptoCurrencyNames(){return cryptoCurrencyNames;}
}
