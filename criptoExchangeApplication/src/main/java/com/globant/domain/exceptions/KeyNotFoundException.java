package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class KeyNotFoundException extends RepositoryException{
    public KeyNotFoundException(String msg) {
        super(msg);
    }
    
    public static KeyNotFoundException keyNotFound(){return new KeyNotFoundException("KEY NOT FOUND");}
}
