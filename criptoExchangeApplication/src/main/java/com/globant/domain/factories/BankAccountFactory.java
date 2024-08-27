package com.globant.domain.factories;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.NumberAccount;
import com.globant.domain.user.PacificoBankAccount;
import com.globant.domain.user.PichinchaBankAccount;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class BankAccountFactory {
    private static final BigDecimal initialMoney = new BigDecimal("100");
    private static final Map<BankName, BankAccountCreationOrder> bankAccounts;
    static {
        bankAccounts = new HashMap<>();
        bankAccounts.put(BankName.PACIFICO, new PacificoAccountCreationOrder());
        bankAccounts.put(BankName.PICHINCHA, new PichinchaAccountCreationOrder());
    }
    
    public PacificoBankAccount createPacificBankAccount(String numberAccount) throws InvalidNumberAccountException{
        return new PacificoBankAccount(new NumberAccount(numberAccount), initialMoney);
    }
    
    public PichinchaBankAccount createPichinchaBankAccount(String numberAccount) throws InvalidNumberAccountException{
        return new PichinchaBankAccount(new NumberAccount(numberAccount), initialMoney);
    }
    
    public BankAccount createAccount(BankName bankName, String numberAccount) throws InvalidNumberAccountException{
        BankAccountCreationOrder creationOrder = bankAccounts.get(bankName);
        return creationOrder.execute(this, numberAccount);
    }
}
