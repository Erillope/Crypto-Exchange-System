package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidPasswordException extends InvalidUserAccountException{
    public InvalidPasswordException(String msg) {
        super(msg);
    }
    
    public static InvalidPasswordException invalidException(){return new InvalidPasswordException("INVALID PASSWORD");}
    
    public static InvalidPasswordException incorrectPassword(){return new InvalidPasswordException("INCORRECT PASSWORD");}
}
