package com.globant.domain.crypto;

/**
 *
 * @author erillope
 */
public class InsufficientCurrencyException extends Exception{
    public InsufficientCurrencyException(String msg) {
        super(msg);
    }
}
