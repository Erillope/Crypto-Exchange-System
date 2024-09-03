package com.globant.domain.factories.creationorders;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.factories.BankAccountFactory;
import com.globant.domain.user.accounts.BankAccount;

/**
 *
 * @author erillope
 */
public interface BankAccountCreationOrder {
    public BankAccount execute(BankAccountFactory factory, String numberAccount) throws InvalidNumberAccountException;
}
