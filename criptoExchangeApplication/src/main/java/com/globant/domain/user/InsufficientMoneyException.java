package com.globant.domain.user;

/**
 *
 * @author erillope
 */
public class InsufficientMoneyException extends Exception{
    public InsufficientMoneyException(String msg) {
        super(msg);
    }
}
