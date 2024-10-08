package com.globant.application.services.authentication;

import com.globant.application.dto.SignUpDTO;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.application.exceptions.UserAlreadyExistException;
import com.globant.domain.factories.BankAccountFactory;
import com.globant.domain.factories.UserAccountFactory;
import com.globant.domain.factories.UserFactory;
import com.globant.domain.factories.WalletFactory;
import com.globant.domain.user.accounts.BankAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.accounts.UserAccount;

/**
 *
 * @author erillope
 */
public class SignUpUseCaseImpl implements SignUpUseCase{
    private final UserRepository userRepository;
    private final Repository<String, BankAccount> bankAccountRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final UserAccountFactory userAccountFactory;
    private final UserFactory userFactory;
    private final WalletFactory walletFactory;
    private final BankAccountFactory bankAccountFactory;

    public SignUpUseCaseImpl(UserRepository userRepository, Repository<String, BankAccount> bankAccountRepository, Repository<WalletID, Wallet> walletRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.walletRepository = walletRepository;
        this.userAccountFactory = new UserAccountFactory();
        this.userFactory = new UserFactory();
        this.walletFactory = new WalletFactory();
        this.bankAccountFactory = new BankAccountFactory();
    }
    
    @Override
    public void signUp(SignUpDTO dto) throws DomainException{
        registerUser(dto);
        registerBankAccount(dto);
    }
    
    private void registerUser(SignUpDTO dto) throws DomainException{
        UserAccount account = userAccountFactory.createAccount(dto.getName(), dto.getEmail(), dto.getPassword());
        Wallet wallet = walletFactory.createWallet();
        User user = userFactory.createUser(dto.getNumberAccount(), wallet.getID(), account);
        if (userRepository.containEmail(user.getUserAccount().getEmail()))
        {throw UserAlreadyExistException.alreadyExist();}
        userRepository.save(user.getUserID(), user);
        walletRepository.save(wallet.getID(), wallet);
    }
    
    private void registerBankAccount(SignUpDTO dto) throws DomainException{
        BankAccount bankAccount = bankAccountFactory.createAccount(dto.getBankName(), dto.getNumberAccount());
        bankAccountRepository.save(dto.getNumberAccount(), bankAccount);
    }
    
}
