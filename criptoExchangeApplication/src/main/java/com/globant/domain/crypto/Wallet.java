package com.globant.domain.crypto;

import com.globant.domain.exceptions.InsufficientCurrencyException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class Wallet implements Serializable{
    private final Map<CryptoCurrencyName, CryptoCurrency> cryptos;
    private WalletID id;
    
    public Wallet(){
        this.cryptos = new HashMap<>();
        cryptos.put(CryptoCurrencyName.BITCOIN, new Bitcoin());
        cryptos.put(CryptoCurrencyName.ETHEREUM, new Ethereum());
        cryptos.put(CryptoCurrencyName.RIPPLE, new Ripple());
        id = new WalletUUID();
    }
    
    public void addAmount(CryptoCurrencyName cryptoName, CryptoCurrency amount){
        CryptoCurrency crypto = cryptos.get(cryptoName);
        cryptos.put(cryptoName, crypto.add(amount));
    }
    
    public void reduceAmount(CryptoCurrencyName cryptoName, CryptoCurrency amount) throws InsufficientCurrencyException{
        CryptoCurrency crypto = cryptos.get(cryptoName);
        cryptos.put(cryptoName, crypto.reduce(amount));
    }
    
    public void put(CryptoCurrencyName name, CryptoCurrency amount){cryptos.put(name, amount);}
    
    public CryptoCurrency get(CryptoCurrencyName name){return cryptos.get(name);}
    
    public WalletID getID(){return id;}
    
    public void setId(WalletID id){this.id = id;}
}
