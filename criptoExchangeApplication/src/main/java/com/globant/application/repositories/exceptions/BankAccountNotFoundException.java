package com.globant.application.repositories.exceptions;
/**
 *
 * @author erillope
 */
public class BankAccountNotFoundException extends KeyNotFoundException{
    public BankAccountNotFoundException(String msg) {
        super(msg);
    }
    
    public static BankAccountNotFoundException bankAccountNotFound()
    {return new BankAccountNotFoundException("BANK ACCOUNT NOT FOUND");}
}