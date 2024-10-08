package com.globant.application.services.wallet;

import com.globant.application.config.Cache;
import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.application.repositories.Repository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.domain.user.accounts.BankAccount;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class DepositeMoneyUseCaseImpl implements DepositeMoneyUseCase{
    private final Repository<String, BankAccount> bankAccountRepository;

    public DepositeMoneyUseCaseImpl(Repository<String, BankAccount> bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void depositeMoney(DepositeMoneyDTO dto) throws DomainException{
        if (dto.getAmount().signum() <= 0){throw InvalidAmountException.invalidAmount();}
        User user = Cache.getGlobalCacheInstance().currentUser;
        BankAccount userBankAccount = bankAccountRepository.get(user.getNumberAccount().getNumberAccount());
        userBankAccount.add(dto.getAmount());
        bankAccountRepository.save(userBankAccount.getNumberAccount().getNumberAccount(), userBankAccount);
    }
    
}
