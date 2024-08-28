package com.globant.domain.exchange;

import java.util.ArrayList;
import java.util.List;
import com.globant.domain.user.UserID;
import com.globant.domain.util.OnlyReadCollection;
import com.globant.domain.util.OnlyReadCollectionImpl;
import java.io.Serializable;

/**
 *
 * @author erillope
 */
public class TransactionHistory implements Serializable{
    private final UserID userID;
    private final List<Transaction> transactions;
    
    public TransactionHistory(UserID userID){
        this.userID = userID;
        transactions = new ArrayList<>();
    }
    
    public UserID getUserID(){return userID;}
    
    public OnlyReadCollection<Transaction> getTransaction(){
        return new OnlyReadCollectionImpl<>(transactions);
    }
    
    public void addTransaction(Transaction transaction){transactions.add(transaction);}
    
}
