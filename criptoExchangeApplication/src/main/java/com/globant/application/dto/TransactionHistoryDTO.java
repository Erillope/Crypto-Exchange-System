package com.globant.application.dto;

import com.globant.domain.exchange.Transaction;
import com.globant.domain.util.OnlyReadCollection;

/**
 *
 * @author erillope
 */
public class TransactionHistoryDTO {
    private final OnlyReadCollection<Transaction> history;
    
    public TransactionHistoryDTO(OnlyReadCollection<Transaction> history){
        this.history = history;
    }
    
    public OnlyReadCollection<Transaction> getHistory(){return history;}
    
}
