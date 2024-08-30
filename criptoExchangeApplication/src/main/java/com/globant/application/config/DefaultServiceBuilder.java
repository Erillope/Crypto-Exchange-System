package com.globant.application.config;

import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.SerExchangeInstance;
import com.globant.application.repositories.TransactionHistorySerRepository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.application.repositories.WalletSerRepository;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.authentication.SignInUseCase;
import com.globant.application.services.authentication.SignInUseCaseImpl;
import com.globant.application.services.authentication.SignUpUseCase;
import com.globant.application.services.authentication.SignUpUseCaseImpl;
import com.globant.application.services.exchange.ExchangeCryptoCurrencyUseCase;
import com.globant.application.services.exchange.ExchangeCryptoCurrencyUseCaseImpl;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.application.services.exchange.PlaceBuyOrderUseCase;
import com.globant.application.services.exchange.PlaceBuyOrderUseCaseImpl;
import com.globant.application.services.exchange.PlaceSaleOrderUseCase;
import com.globant.application.services.exchange.PlaceSaleOrderUseCaseImpl;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.application.services.wallet.BankTransactionExecuterImpl;
import com.globant.application.services.wallet.DepositeMoneyUseCase;
import com.globant.application.services.wallet.DepositeMoneyUseCaseImpl;
import com.globant.application.services.wallet.GetTransactionHistoryUseCase;
import com.globant.application.services.wallet.GetTransactionHistoryUseCaseImpl;
import com.globant.application.services.wallet.GetWalletBalanceUseCase;
import com.globant.application.services.wallet.GetWalletBalanceUseCaseImpl;
import com.globant.application.services.wallet.WalletService;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class DefaultServiceBuilder implements ServiceBuilder{
    private final UserRepository userRepository;
    private final Repository<String, BankAccount> bankAccountRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;
    private final ExchangeInstance exchangeInstance;
    private final BankTransactionExecuter transactionExecuter;
    
    public DefaultServiceBuilder(){
        userRepository = UserSerRepository.getInstance();
        bankAccountRepository = BankAccountSerRepository.getInstance();
        transactionHistoryRepository = TransactionHistorySerRepository.getInstance();
        walletRepository = WalletSerRepository.getInstance();
        exchangeInstance = SerExchangeInstance.getInstance();
        transactionExecuter = new BankTransactionExecuterImpl();
    }
    
    @Override
    public AuthenticationService buildAuthenticationService() {
        SignUpUseCase signUpUseCase = new SignUpUseCaseImpl(userRepository, bankAccountRepository, walletRepository);
        SignInUseCase signInUseCase = new SignInUseCaseImpl(userRepository);
        return new AuthenticationService(signUpUseCase, signInUseCase, userRepository);
    }

    @Override
    public WalletService buildWalletService() {
        DepositeMoneyUseCase depositeMoneyUseCase = new DepositeMoneyUseCaseImpl(userRepository, bankAccountRepository);
        GetTransactionHistoryUseCase getTransactionHistoryUseCase = new GetTransactionHistoryUseCaseImpl(transactionHistoryRepository);
        GetWalletBalanceUseCase getWalletBalanceUseCase = new GetWalletBalanceUseCaseImpl(userRepository, walletRepository, bankAccountRepository);
        return new WalletService(depositeMoneyUseCase, getTransactionHistoryUseCase, getWalletBalanceUseCase);
    }

    @Override
    public ExchangeService buildExchangeService() {
        ExchangeCryptoCurrencyUseCase exchangeCryptoCurrencyUseCase = new ExchangeCryptoCurrencyUseCaseImpl(userRepository, bankAccountRepository,
        exchangeInstance, transactionExecuter, walletRepository);
        PlaceBuyOrderUseCase placeBuyOrderUseCase = new PlaceBuyOrderUseCaseImpl(userRepository, walletRepository, bankAccountRepository,
        exchangeInstance, transactionHistoryRepository, transactionExecuter);
        PlaceSaleOrderUseCase placeSaleOrderUseCase = new PlaceSaleOrderUseCaseImpl(userRepository, walletRepository, bankAccountRepository,
        exchangeInstance, transactionHistoryRepository, transactionExecuter);
        return new ExchangeService(exchangeCryptoCurrencyUseCase, placeBuyOrderUseCase, placeSaleOrderUseCase);
    }

    @Override
    public Initializer buildInitializer() {
        return new ExchangeInitializer(walletRepository, bankAccountRepository, exchangeInstance);
    }
    
}
