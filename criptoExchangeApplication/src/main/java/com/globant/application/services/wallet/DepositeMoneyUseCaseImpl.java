package com.globant.application.services.wallet;

import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class DepositeMoneyUseCaseImpl implements DepositeMoneyUseCase{
    private final UserRepository userRepository;
    private final Repository<String, BankAccount> bankAccountRepository;
    private final ExchangeInstance exchangeInstance;
    private final BankTransactionExecuter transactionExecuter;

    public DepositeMoneyUseCaseImpl(UserRepository userRepository, Repository<String, BankAccount> bankAccountRepository, 
            ExchangeInstance exchangeInstance, BankTransactionExecuter transactionExecuter) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionExecuter = transactionExecuter;
    }

    @Override
    public void depositeMoney(DepositeMoneyDTO dto) throws DomainException{
        User user = userRepository.get(dto.getUserID());
        Exchange exchange = exchangeInstance.get();
        BankAccount userBankAccount = bankAccountRepository.get(user.getNumberAccount().getNumberAccount());
        BankAccount exchangeBankAccount = bankAccountRepository.get(exchange.getNumberAccount().getNumberAccount());
        transactionExecuter.execute(userBankAccount, exchangeBankAccount, dto.getAmount());
        bankAccountRepository.save(userBankAccount.getNumberAccount().getNumberAccount(), userBankAccount);
        bankAccountRepository.save(exchangeBankAccount.getNumberAccount().getNumberAccount(), exchangeBankAccount);
    }
    
}
