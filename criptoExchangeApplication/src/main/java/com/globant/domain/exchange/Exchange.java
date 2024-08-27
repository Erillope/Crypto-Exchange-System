package com.globant.domain.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.crypto.WalletUUID;
import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.NumberAccount;

/**
 *
 * @author erillope
 */
public class Exchange {
    private static Exchange instance = null;
    private final static Map<CryptoCurrencyName, BigDecimal> initialPrices;
    static {
        initialPrices = new HashMap<>();
        initialPrices.put(CryptoCurrencyName.BITCOIN, new BigDecimal("10"));
        initialPrices.put(CryptoCurrencyName.ETHEREUM, new BigDecimal("5"));
        initialPrices.put(CryptoCurrencyName.RIPPLE, new BigDecimal("3"));
    }
    
    private NumberAccount numberAccount;
    private WalletID walletID;
    private Map<CryptoCurrencyName, BigDecimal> marketPrices;
    
    private Exchange(){
        try{numberAccount = new NumberAccount("123456789");}
        catch(InvalidNumberAccountException e){e.printStackTrace();}
        walletID = new WalletUUID();
        marketPrices = new HashMap<>();
    }
    
    public NumberAccount getNumberAccount(){return numberAccount;}
    
    public WalletID getWalletID(){return walletID;}
    
    public void setWalletID(WalletID id){walletID = id;}
    
    public BigDecimal getPrice(CryptoCurrencyName cryptoName){return marketPrices.get(cryptoName);}
    
    public void addPrice(CryptoCurrencyName cryptoName, BigDecimal price){marketPrices.put(cryptoName, price);}
    
    public static Exchange getInstance(){
        if (instance != null){return instance;}
        return new Exchange();
    }
    
    public static BigDecimal getInitialPrice(CryptoCurrencyName cryptoName){return initialPrices.get(cryptoName);}
 }