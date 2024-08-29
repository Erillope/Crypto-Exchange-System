package com.globant.application.dto;

import com.globant.domain.crypto.WalletID;
import com.globant.domain.user.NumberAccount;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class UserDTO {
    private final String numberAccount;
    private final UserID id;
    private final WalletID walletID;
    private final String name;
    private final String email;

    public UserDTO(String numberAccount, UserID id, WalletID walletID, String name, String email) {
        this.numberAccount = numberAccount;
        this.id = id;
        this.walletID = walletID;
        this.name = name;
        this.email = email;
    }

    public String getNumberAccount(){return numberAccount;}

    public UserID getId(){return id;}

    public WalletID getWalletID(){return walletID;}

    public String getName(){return name;}

    public String getEmail(){return email;}
}
