package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InsufficientCurrencyException extends DomainException{
    public InsufficientCurrencyException(String msg) {
        super(msg);
    }
}
