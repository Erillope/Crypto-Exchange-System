package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidNumberAccountException extends DomainException{
    public InvalidNumberAccountException(String msg) {
        super(msg);
    }
    
    public static InvalidNumberAccountException invalidNumberAccount(){
        return new InvalidNumberAccountException("INVALID NUMBER ACCOUNT:\n"
                + "The account number must be\n"
                + "between 8 and 12 digits long.");
    }
}
