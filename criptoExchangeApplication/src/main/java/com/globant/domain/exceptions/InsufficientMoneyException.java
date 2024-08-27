package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InsufficientMoneyException extends DomainException{
    public InsufficientMoneyException(String msg) {
        super(msg);
    }
    
    public static InsufficientMoneyException insufficientMoney(){return new InsufficientMoneyException("INSUFFICIENT MONEY");}
}
