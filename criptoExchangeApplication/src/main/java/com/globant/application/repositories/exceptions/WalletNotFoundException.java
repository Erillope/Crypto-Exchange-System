package com.globant.application.repositories.exceptions;

/**
 *
 * @author erillope
 */
public class WalletNotFoundException extends KeyNotFoundException{
    public WalletNotFoundException(String msg) {
        super(msg);
    }
    
    public static WalletNotFoundException walletNotFound(){return new WalletNotFoundException("WALLET NOT FOUND");}
}