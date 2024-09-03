package com.globant.application.dto;

import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.util.OnlyReadMap;

/**
 *
 * @author erillope
 */
public class BalanceDTO {
    private final BigDecimal money;
    private final OnlyReadMap<CryptoCurrencyName, BigDecimal> cryptoCurrencyBalance;
    
    public BalanceDTO(BigDecimal money, OnlyReadMap<CryptoCurrencyName, BigDecimal> cryptoCurrencyBalance){
        this.money = money;
        this.cryptoCurrencyBalance = cryptoCurrencyBalance;
    }
    
    public BigDecimal getMoney(){return money;}
    
    public OnlyReadMap getCryptoCurrencyBalance(){return cryptoCurrencyBalance;}
}
