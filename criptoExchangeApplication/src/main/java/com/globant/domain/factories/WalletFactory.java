package com.globant.domain.factories;

import com.globant.domain.crypto.Wallet;

/**
 *
 * @author erillope
 */
public class WalletFactory {
    public Wallet createWallet(){
        return new Wallet();
    }
}
