package com.globant.domain.user;

import com.globant.domain.exceptions.InsufficientMoneyException;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class PichinchaBankAccount extends BankAccount{

    public PichinchaBankAccount(NumberAccount numberAccount, BigDecimal money) {
        super(numberAccount, money);
    }

    @Override
    public void add(BigDecimal money) {
        this.money = this.money.add(money);
    }

    @Override
    public void reduce(BigDecimal money) throws InsufficientMoneyException {
        if (this.money.compareTo(money) < 0){throw InsufficientMoneyException.insufficientMoney();}
        this.money = this.money.subtract(money);
    }
    
}
