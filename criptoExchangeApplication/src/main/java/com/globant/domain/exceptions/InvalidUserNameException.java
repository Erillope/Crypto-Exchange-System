package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidUserNameException extends InvalidUserAccountException{
    public InvalidUserNameException(String msg) {
        super(msg);
    }
    
    public static InvalidUserNameException invalidName(){return new InvalidUserNameException("INVALID NAME");}
}
