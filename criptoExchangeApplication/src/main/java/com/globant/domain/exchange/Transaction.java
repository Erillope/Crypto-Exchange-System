package com.globant.domain.exchange;

import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Transaction implements Serializable{
    private final BigDecimal amount;
    private final CryptoCurrencyName cryptoName;
    private final UserID userID;
    private final TransactionType type;
    private final BigDecimal price;
    
    public Transaction(BigDecimal price, BigDecimal amount, CryptoCurrencyName cryptoName, UserID userID, TransactionType type){
        this.amount = amount;
        this.cryptoName = cryptoName;
        this.userID = userID;
        this.type = type;
        this.price = price;
    }
    
    public TransactionType getType(){return type;}
    
    public BigDecimal getPrice(){return price;}
            
    public BigDecimal getAmount(){return amount;}
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}
    
    public UserID getUserID(){return userID;}

    @Override
    public String toString() {
        return "Transaction{" + "amount=" + amount + ", cryptoName=" + cryptoName + ", type=" + type + ", price=" + price + '}';
    }    
    
}
