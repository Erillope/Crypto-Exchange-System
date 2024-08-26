package application.dto;

import models.exchange.Transaction;
import models.util.OnlyReadCollection;

/**
 *
 * @author erillope
 */
public class TransactionHistoryDTO {
    private OnlyReadCollection<Transaction> history;
    
    public TransactionHistoryDTO(OnlyReadCollection<Transaction> history){
        this.history = history;
    }
    
    public OnlyReadCollection<Transaction> getHistory(){return history;}
    
}
