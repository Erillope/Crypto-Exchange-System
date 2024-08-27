package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidUserAccountException extends DomainException{
    public InvalidUserAccountException(String msg) {
        super(msg);
    }
}
