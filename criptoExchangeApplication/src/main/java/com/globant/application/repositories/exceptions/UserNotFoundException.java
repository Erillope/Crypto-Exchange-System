package com.globant.application.repositories.exceptions;
/**
 *
 * @author erillope
 */
public class UserNotFoundException extends KeyNotFoundException{
    public UserNotFoundException(String msg) {
        super(msg);
    }
    
    public static UserNotFoundException userNotFound(){return new UserNotFoundException("USER NOT FOUND");}
}
