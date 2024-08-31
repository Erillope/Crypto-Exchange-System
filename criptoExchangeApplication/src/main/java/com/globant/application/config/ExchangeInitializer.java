package com.globant.application.config;

import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.domain.crypto.Bitcoin;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.factories.BankAccountFactory;
import com.globant.domain.factories.BankName;
import com.globant.domain.factories.WalletFactory;
import com.globant.domain.user.BankAccount;
import java.math.BigDecimal;
import java.util.prefs.Preferences;

/**
 *
 * @author erillope
 */
public class ExchangeInitializer implements Initializer{
    public final Repository<WalletID, Wallet> walletRepository;
    public final Repository<String, BankAccount> bankAccountRepository;
    public final ExchangeInstance exchangeInstance;
    public final WalletFactory walletFactory;
    public final BankAccountFactory bankAccountFactory;

    public ExchangeInitializer(Repository<WalletID, Wallet> walletRepository, Repository<String, BankAccount> 
            bankAccountRepository, ExchangeInstance exchangeInstance) {
        this.walletRepository = walletRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.exchangeInstance = exchangeInstance;
        this.walletFactory = new WalletFactory();
        this.bankAccountFactory = new BankAccountFactory();
    }

    @Override
    public void init() throws DomainException{
        Preferences prefs = Preferences.userNodeForPackage(ExchangeInitializer.class);
        //prefs.putBoolean("FirstExecution", true);
        if (prefs.getBoolean("FirstExecution", true)){
            initExchange();
            prefs.putBoolean("FirstExecution", false);
        }
    }
    
    public void initExchange() throws DomainException{
        Exchange exchange = exchangeInstance.get();
        Wallet exchangeWallet = walletFactory.createWallet();
        exchange.addPrice(CryptoCurrencyName.BITCOIN, BigDecimal.TEN);
        exchangeWallet.addAmount(CryptoCurrencyName.BITCOIN, new Bitcoin(BigDecimal.TEN));
        BankAccount exchangeBankAccount = bankAccountFactory.createAccount(BankName.PACIFICO, exchange.getNumberAccount().getNumberAccount());
        exchange.setWalletID(exchangeWallet.getID());
        walletRepository.save(exchange.getWalletID(), exchangeWallet);
        bankAccountRepository.save(exchangeBankAccount.getNumberAccount().getNumberAccount(), exchangeBankAccount);
        exchangeInstance.save(exchange);
    }
    
}
