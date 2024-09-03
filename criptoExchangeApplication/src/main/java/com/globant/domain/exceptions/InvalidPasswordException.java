package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidPasswordException extends InvalidUserAccountException{
    public InvalidPasswordException(String msg) {
        super(msg);
    }
    
    public static InvalidPasswordException invalidException()
    {return new InvalidPasswordException("INVALID PASSWORD:\n"
            + "The password must have\n"
            + "more than 8 characters,\n"
            + "one uppercase letter,\n"
            + "one lowercase letter\n"
            + "and at least one numeric digit.");}
    
    public static InvalidPasswordException incorrectPassword(){return new InvalidPasswordException("INCORRECT PASSWORD");}
}
