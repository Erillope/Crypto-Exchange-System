package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.user.UserAccount;

/**
 *
 * @author erillope
 */
public interface EmailCreationOrder {
   public UserAccount execute(UserAccountFactory factory, String name, String email, String password) throws InvalidUserAccountException;
}
