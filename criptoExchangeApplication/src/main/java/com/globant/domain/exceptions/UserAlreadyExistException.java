package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class UserAlreadyExistException extends ApplicationException{
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
    
    public static UserAlreadyExistException alreadyExist(){return new UserAlreadyExistException("THIS USER ALREADY EXIST");}
}
