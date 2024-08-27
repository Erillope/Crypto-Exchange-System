package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class InvalidAmountException extends DomainException {
    public InvalidAmountException(String msg) {
        super(msg);
    }
}
