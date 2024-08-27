package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.BankAccount;

/**
 *
 * @author erillope
 */
public class PichinchaAccountCreationOrder implements BankAccountCreationOrder{

    @Override
    public BankAccount execute(BankAccountFactory factory, String numberAccount) throws InvalidNumberAccountException {
        return factory.createPichinchaBankAccount(numberAccount);
    }
    
}
