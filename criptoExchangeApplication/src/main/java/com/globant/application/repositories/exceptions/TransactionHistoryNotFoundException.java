package com.globant.application.repositories.exceptions;

/**
 *
 * @author erillope
 */
public class TransactionHistoryNotFoundException extends KeyNotFoundException{
    public TransactionHistoryNotFoundException(String msg) {
        super(msg);
    }
    
    public static TransactionHistoryNotFoundException transactionHistoryNotFound()
    {return new TransactionHistoryNotFoundException("TRANSACTION HISTORY NOT FOUND");}
}