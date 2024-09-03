package com.globant.domain.user;

import com.globant.domain.user.accounts.UserAccount;
import com.globant.domain.user.accounts.NumberAccount;
import java.math.BigDecimal;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.SalesOrder;
import java.io.Serializable;

/**
 *
 * @author erillope
 */
public class User implements Serializable{
    private final NumberAccount numberAccount;
    private final UserID id;
    private final WalletID walletID;
    private final UserAccount account;
    
    public User(NumberAccount numberAccount, WalletID walletID, UserAccount account){
        this.numberAccount = numberAccount;
        this.walletID = walletID;
        this.account = account;
        this.id = new UserUUID();
    }
    
    public SalesOrder generateSalesOrder(CryptoCurrency amount, CryptoCurrencyName cryptoCurrency, BigDecimal minPrice){
        return new SalesOrder(amount, cryptoCurrency, id, minPrice);
    }
    
    public BuyOrder generateBuyOrder(CryptoCurrency amount, CryptoCurrencyName cryptoCurrency, BigDecimal maxPrice){
        return new BuyOrder(amount, cryptoCurrency, id, maxPrice);
    }
    
    public NumberAccount getNumberAccount(){return numberAccount;}
    
    public UserID getUserID(){return id;}
    
    public WalletID getWalletID(){return walletID;}
    
    public UserAccount getUserAccount(){return account;}
};