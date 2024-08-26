package application.dto;

import java.math.BigDecimal;
import java.util.Map;
import models.crypto.CryptoCurrencyName;
import models.util.OnlyReadMap;
import models.util.OnlyReadMapImpl;

/**
 *
 * @author erillope
 */
public class BalanceDTO {
    public final BigDecimal money;
    public final OnlyReadMap<CryptoCurrencyName, BigDecimal> cryptoCurrencyBalance;
    
    public BalanceDTO(BigDecimal money, Map<CryptoCurrencyName, BigDecimal> cryptoCurrencyBalance){
        this.money = money;
        this.cryptoCurrencyBalance = new OnlyReadMapImpl<>(cryptoCurrencyBalance);
    }
    
    public BigDecimal getMoney(){return money;}
    
    public OnlyReadMap getCryptoCurrencyBalance(){return cryptoCurrencyBalance;}
}
