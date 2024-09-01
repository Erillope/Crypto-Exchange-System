package com.globant.application.services.exchange;

import com.globant.application.config.ApplicationCache;
import com.globant.application.dto.AvailableCoinsDTO;
import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.repositories.Repository;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InsufficientCurrencyException;
import com.globant.domain.exceptions.InsufficientMoneyException;
import com.globant.domain.exchange.Transaction;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.exchange.TransactionType;
import com.globant.domain.factories.CryptoCurrencyFactory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.UserID;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyUseCaseImpl implements ExchangeCryptoCurrencyUseCase{
    private final Repository<String, BankAccount> bankAccountRepository;
    private final BankTransactionExecuter transactionExecuter;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final CryptoCurrencyFactory cryptoFactory;
    private final ApplicationCache cache = ApplicationCache.getInstance();

    public ExchangeCryptoCurrencyUseCaseImpl(Repository<String, BankAccount> bankAccountRepository, BankTransactionExecuter transactionExecuter, 
            Repository<WalletID, Wallet> walletRepository, Repository<UserID, TransactionHistory> transactionHistoryRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionExecuter = transactionExecuter;
        this.walletRepository = walletRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        cryptoFactory = new CryptoCurrencyFactory();
    }
    
    @Override
    public void exchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        validateExchange(dto);
        exchangeCryptoCurrency(dto);
        executePayment(dto);
        addTransaction(dto);
    }
    
    @Override
    public AvailableCoinsDTO getAvailableCoins() throws DomainException {
        return new AvailableCoinsDTO(cache.exchangeWallet.getCryptos(), cache.exchange.getMarketPrices());
    }
    
    private void validateExchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        BigDecimal exchangeAmount = cache.exchangeWallet.get(dto.getCryptoName()).getAmount();
        BigDecimal totalAmount = dto.getAmount().multiply(cache.exchange.getPrice(dto.getCryptoName()));
        boolean isEnoughMoney = cache.currentUserBankAccount.getMoney().compareTo(totalAmount) > 0;
        boolean isEnoughCryptoCurrency = exchangeAmount.compareTo(dto.getAmount()) > 0;
        if (!isEnoughMoney){throw InsufficientCurrencyException.insufficientAmount();}
        if (!isEnoughCryptoCurrency){throw InsufficientMoneyException.insufficientMoney();}
    }
    
    private void exchangeCryptoCurrency(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        CryptoCurrency amount = cryptoFactory.createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        cache.exchangeWallet.reduceAmount(dto.getCryptoName(), amount);
        cache.currentUserWallet.addAmount(dto.getCryptoName(), amount);
        walletRepository.save(cache.exchangeWallet.getID(), cache.exchangeWallet);
        walletRepository.save(cache.currentUserWallet.getID(), cache.currentUserWallet);
    }
    
    private void executePayment(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        BigDecimal totalAmount = dto.getAmount().multiply(cache.exchange.getPrice(dto.getCryptoName()));
        transactionExecuter.execute(cache.currentUserBankAccount, cache.exchangeBankAccount, totalAmount);
        bankAccountRepository.save(cache.currentUserBankAccount.getNumberAccount().getNumberAccount(), cache.currentUserBankAccount);
        bankAccountRepository.save(cache.exchangeBankAccount.getNumberAccount().getNumberAccount(), cache.exchangeBankAccount);
    }
    
    private void addTransaction(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        BigDecimal totalPrice = dto.getAmount().multiply(cache.exchange.getPrice(dto.getCryptoName()));
        Transaction transaction = new Transaction(totalPrice, dto.getCryptoName(), dto.getUserID(), TransactionType.BUY);
        cache.currentUserTransactionHistory.addTransaction(transaction);
        transactionHistoryRepository.save(dto.getUserID(), cache.currentUserTransactionHistory);
    }
}
