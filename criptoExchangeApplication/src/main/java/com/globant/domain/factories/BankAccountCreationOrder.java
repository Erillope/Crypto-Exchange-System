package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.NumberAccount;

/**
 *
 * @author erillope
 */
public interface BankAccountCreationOrder {
    public BankAccount execute(BankAccountFactory factory, String numberAccount) throws InvalidNumberAccountException;
}
