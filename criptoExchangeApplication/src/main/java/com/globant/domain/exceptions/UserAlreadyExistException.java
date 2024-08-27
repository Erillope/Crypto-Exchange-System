package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class UserAlreadyExistException extends DomainException{
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
