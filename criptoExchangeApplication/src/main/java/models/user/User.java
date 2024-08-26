package models.user;

import java.math.BigDecimal;
import models.crypto.CryptoCurrency;
import models.crypto.CryptoCurrencyName;
import models.crypto.WalletID;
import models.exchange.SalesOrder;

/**
 *
 * @author erillope
 */
public class User {
    private final String numberAccount;
    private final UserID id;
    private final WalletID walletID;
    private final UserAccount account;
    
    public User(String numberAccount, WalletID walletID, UserAccount account){
        this.numberAccount = numberAccount;
        this.walletID = walletID;
        this.account = account;
        this.id = new UserUUID();
    }
    
    public SalesOrder generateSalesOrder(CryptoCurrency amount, CryptoCurrencyName cryptoCurrency, BigDecimal minPrice){
        return new SalesOrder(amount, cryptoCurrency, id, minPrice);
    }
    
    public String getNumberAccount(){return numberAccount;}
    
    public UserID getUserID(){return id;}
    
    public WalletID getWalletID(){return walletID;}
    
    public UserAccount getUserAccount(){return account;}
};