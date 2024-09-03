package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidEmailException extends InvalidUserAccountException{
    public InvalidEmailException(String msg) {
        super(msg);
    }
    
    public static InvalidEmailException invalidEmail()
    {return new InvalidEmailException("INVALID EMAIL:\nOnly Gmail and Outlook \nare valid");}

}
