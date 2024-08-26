package com.globant.domain.exchange;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public abstract class Order {
    private final CryptoCurrency amount;
    private final CryptoCurrencyName cryptoName;
    private final UserID userID;
    
    public Order(CryptoCurrency amount, CryptoCurrencyName cryptoName, UserID userID){
        this.amount = amount;
        this.cryptoName = cryptoName;
        this.userID = userID;
    }
    
    protected abstract void verifyAmount() throws InvalidAmountException;
    
    public CryptoCurrency getAmount(){return amount;}
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}
    
    public UserID getUserID(){return userID;}
}
