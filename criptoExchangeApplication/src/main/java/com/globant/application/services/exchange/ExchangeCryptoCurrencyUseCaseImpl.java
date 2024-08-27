package com.globant.application.services.exchange;

import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyUseCaseImpl implements ExchangeCryptoCurrencyUseCase{
    private final UserRepository userRepository;
    private final Repository<String, BankAccount> bankAccountRepository;
    private final ExchangeInstance exchangeInstance;
    private final BankTransactionExecuter transactionExecuter;
    private final Repository<WalletID, Wallet> walletRepository;

    public ExchangeCryptoCurrencyUseCaseImpl(UserRepository userRepository, Repository<String,
            BankAccount> bankAccountRepository, ExchangeInstance exchangeInstance, BankTransactionExecuter transactionExecuter, Repository<WalletID, Wallet> walletRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.exchangeInstance = exchangeInstance;
        this.transactionExecuter = transactionExecuter;
        this.walletRepository = walletRepository;
    }
    
    @Override
    public void exchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        validateExchange(dto);
        exchangeCryptoCurrency(dto);
        executePayment(dto);
    }
    
    private void validateExchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        Exchange exchange = exchangeInstance.get();
        Wallet exchangeWallet = walletRepository.get(exchange.getWalletID());
        BigDecimal exchangeAmount = exchangeWallet.get(dto.getCryptoName()).getAmount();
        if (dto.getAmount().getAmount().compareTo(exchangeAmount) > 0)
        {throw InsufficientCurrencyException.insufficientAmount();}
    }
    
    private void exchangeCryptoCurrency(ExchangeCryptoCurrencyDTO dto)throws DomainException{
        Exchange exchange = exchangeInstance.get();
        User user = userRepository.get(dto.getUserID());
        Wallet exchangeWallet = walletRepository.get(exchange.getWalletID());
        Wallet userWallet = walletRepository.get(user.getWalletID());
        exchangeWallet.reduceAmount(dto.getCryptoName(), dto.getAmount());
        userWallet.addAmount(dto.getCryptoName(), dto.getAmount());
        walletRepository.save(exchangeWallet.getID(), exchangeWallet);
        walletRepository.save(userWallet.getID(), userWallet);
    }
    
    private void executePayment(ExchangeCryptoCurrencyDTO dto)throws DomainException{
        User user = userRepository.get(dto.getUserID());
        Exchange exchange = exchangeInstance.get();
        BankAccount userBankAccount = bankAccountRepository.get(user.getNumberAccount().getNumberAccount());
        BankAccount exchangeBankAccount = bankAccountRepository.get(exchange.getNumberAccount().getNumberAccount());
        BigDecimal totalAmount = dto.getAmount().getAmount().multiply(Exchange.getInitialPrice(dto.getCryptoName()));
        transactionExecuter.execute(userBankAccount, exchangeBankAccount, totalAmount);
        bankAccountRepository.save(userBankAccount.getNumberAccount().getNumberAccount(), userBankAccount);
        bankAccountRepository.save(exchangeBankAccount.getNumberAccount().getNumberAccount(), exchangeBankAccount);
    }
}
