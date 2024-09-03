package com.globant.application.services.wallet;

import com.globant.domain.exceptions.BankException;
import com.globant.domain.user.accounts.BankAccount;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public interface BankTransactionExecuter {
    public void execute(BankAccount payer, BankAccount beneficiary, BigDecimal amount) throws BankException;
}
