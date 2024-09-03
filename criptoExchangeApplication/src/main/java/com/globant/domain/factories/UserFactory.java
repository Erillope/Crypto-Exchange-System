package com.globant.domain.factories;

import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.accounts.NumberAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.accounts.UserAccount;

/**
 *
 * @author eriilope
 */
public class UserFactory {
    public User createUser(String numberAccount, WalletID walletID, UserAccount account) throws InvalidNumberAccountException{
        return new User(new NumberAccount(numberAccount), walletID, account);
    }
}
