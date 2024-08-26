package com.globant.domain.crypto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class Wallet {
    private final Map<CryptoCurrencyName, CryptoCurrency> cryptos;
    
    public Wallet(){
        this.cryptos = new HashMap();
    }
    
    public void put(CryptoCurrencyName name, CryptoCurrency amount){cryptos.put(name, amount);}
    
    public CryptoCurrency get(CryptoCurrencyName name){return cryptos.get(name);}
    
    public void addAmount(IncomePayment incomePayment, CryptoCurrency amount){
        incomePayment.addAmount(this, amount);
    }
    
    public void reduceAmount(ExpencePayment expencePayment, CryptoCurrency amount){
        expencePayment.reduceAmount(this, amount);
    }
}
