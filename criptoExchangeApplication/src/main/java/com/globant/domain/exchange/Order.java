package com.globant.domain.exchange;

import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.user.UserID;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public abstract class Order implements Serializable{
    private final CryptoCurrency amount;
    private final CryptoCurrencyName cryptoName;
    private final UserID userID;
    private BigDecimal exchangedAmount;
    private BigDecimal exchangedMoney;
    
    public Order(CryptoCurrency amount, CryptoCurrencyName cryptoName, UserID userID){
        this.amount = amount;
        this.cryptoName = cryptoName;
        this.userID = userID;
        this.exchangedAmount = BigDecimal.ZERO;
        this.exchangedMoney = BigDecimal.ZERO;
    }
        
    public CryptoCurrency getAmount(){return amount;}
    
    public CryptoCurrencyName getCryptoName(){return cryptoName;}
    
    public UserID getUserID(){return userID;}
    
    public BigDecimal getRemainigAmount(){return amount.getAmount().subtract(exchangedAmount);}
    
    public BigDecimal getExchangedMoney(){return exchangedMoney;}
    
    public void exchange(BigDecimal amount){
        if (amount.compareTo(getRemainigAmount()) >= 0){exchangedAmount = exchangedAmount.add(getRemainigAmount());}
        else{exchangedAmount = exchangedAmount.add(amount);}
    }
    
    public void exchangeMoney(BigDecimal money){this.exchangedMoney = this.exchangedMoney.add(money);}
    
    public boolean isCompleted(){return getRemainigAmount().compareTo(BigDecimal.ZERO) == 0;}
}
