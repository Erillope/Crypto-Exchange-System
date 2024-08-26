package com.globant.domain.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.crypto.WalletUUID;

/**
 *
 * @author erillope
 */
public class Exchange {
    private static Exchange instance = null;
    
    private final String numberAccount;
    private WalletID walletID;
    private final Map<CryptoCurrencyName, BigDecimal> marketPrices;
    
    private Exchange(){
        numberAccount = "7676543879";
        walletID = new WalletUUID();
        marketPrices = new HashMap<>();
    }
    
    public String getNumberAccount(){return numberAccount;}
    
    public WalletID getWalletID(){return walletID;}
    
    public void setWalletID(WalletID id){walletID = id;}
    
    public BigDecimal getPrice(CryptoCurrencyName cryptoName){return marketPrices.get(cryptoName);}
    
    public void addPrice(CryptoCurrencyName cryptoName, BigDecimal price){marketPrices.put(cryptoName, price);}
    
    public static Exchange getInstance(){
        if (instance != null){return instance;}
        return new Exchange();
    }
    
    public static void loadInstance(Exchange instance){Exchange.instance = instance;}
}