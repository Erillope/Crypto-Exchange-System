package models.exchange;

import java.util.ArrayList;
import java.util.List;
import models.user.UserID;
import models.util.OnlyReadCollection;
import models.util.OnlyReadCollectionImpl;

/**
 *
 * @author erillope
 */
public class TransactionHistory {
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
    
}
