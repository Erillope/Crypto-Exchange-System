package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.BankAccount;

/**
 *
 * @author erillope
 */
public class PacificoAccountCreationOrder implements BankAccountCreationOrder{

    @Override
    public BankAccount execute(BankAccountFactory factory, String numberAccount) throws InvalidNumberAccountException {
        return factory.createPacificBankAccount(numberAccount);
    }
    
}
