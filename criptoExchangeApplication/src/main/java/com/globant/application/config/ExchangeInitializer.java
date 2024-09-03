package com.globant.application.config;

import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.domain.crypto.Bitcoin;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.Ethereum;
import com.globant.domain.crypto.Ripple;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.factories.BankAccountFactory;
import com.globant.domain.user.accounts.BankName;
import com.globant.domain.factories.WalletFactory;
import com.globant.domain.user.accounts.BankAccount;
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
        if (prefs.getBoolean("FirstExecution", true)){
            initExchange();
            prefs.putBoolean("FirstExecution", false);
        }
    }
    
    public void initExchange() throws DomainException{
        Exchange exchange = exchangeInstance.get();
        Wallet exchangeWallet = walletFactory.createWallet();
        exchange.updatePrice(CryptoCurrencyName.BITCOIN, new BigDecimal("1000"));
        exchange.updatePrice(CryptoCurrencyName.ETHEREUM, new BigDecimal("500"));
        exchange.updatePrice(CryptoCurrencyName.RIPPLE, new BigDecimal("700"));
        exchangeWallet.addAmount(CryptoCurrencyName.BITCOIN, new Bitcoin(new BigDecimal("300")));
        exchangeWallet.addAmount(CryptoCurrencyName.ETHEREUM, new Ethereum(new BigDecimal("200")));
        exchangeWallet.addAmount(CryptoCurrencyName.RIPPLE, new Ripple(new BigDecimal("200")));
        BankAccount exchangeBankAccount = bankAccountFactory.createAccount(BankName.PACIFICO, exchange.getNumberAccount().getNumberAccount());
        exchange.setWalletID(exchangeWallet.getID());
        walletRepository.save(exchange.getWalletID(), exchangeWallet);
        bankAccountRepository.save(exchangeBankAccount.getNumberAccount().getNumberAccount(), exchangeBankAccount);
        exchangeInstance.save(exchange);
    }
    
}
