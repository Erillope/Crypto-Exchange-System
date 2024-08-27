package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.user.UserAccount;

/**
 *
 * @author erillope
 */
public class GmailCreationOrder implements EmailCreationOrder{

    @Override
    public UserAccount execute(UserAccountFactory factory, String name, String email, String password) throws InvalidUserAccountException {
        return factory.createGmailAccount(name, email, password);
    }
    
}
