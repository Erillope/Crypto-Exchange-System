package com.globant.domain.factories.creationorders;

import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.factories.UserAccountFactory;
import com.globant.domain.user.accounts.UserAccount;

/**
 *
 * @author erillope
 */
public interface EmailCreationOrder {
   public UserAccount execute(UserAccountFactory factory, String name, String email, String password) throws InvalidUserAccountException;
}
