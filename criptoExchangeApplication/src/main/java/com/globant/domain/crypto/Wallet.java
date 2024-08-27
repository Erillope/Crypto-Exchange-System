package com.globant.domain.crypto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class Wallet implements Serializable{
    private final Map<CryptoCurrencyName, CryptoCurrency> cryptos;
    private final WalletID id;
    
    public Wallet(){
        this.cryptos = new HashMap();
        id = new WalletUUID();
    }
    
    public void addAmount(IncomePayment incomePayment, CryptoCurrency amount){
        incomePayment.addAmount(this, amount);
    }
    
    public void reduceAmount(ExpencePayment expencePayment, CryptoCurrency amount){
        expencePayment.reduceAmount(this, amount);
    }
    
    public void put(CryptoCurrencyName name, CryptoCurrency amount){cryptos.put(name, amount);}
    
    public CryptoCurrency get(CryptoCurrencyName name){return cryptos.get(name);}
    
    public WalletID getID(){return id;}
}
