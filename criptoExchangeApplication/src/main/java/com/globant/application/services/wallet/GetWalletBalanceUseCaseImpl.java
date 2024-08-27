package com.globant.application.services.wallet;

import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceUseCaseImpl implements GetWalletBalanceUseCase{
    private final UserRepository userRepository;
    private final Repository<WalletID, Wallet> walletRepository;
    private final Repository<String, BankAccount> bankAccount;

    public GetWalletBalanceUseCaseImpl(UserRepository userRepository, Repository<WalletID, Wallet> walletRepository,
            Repository<String, BankAccount> bankAccount) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.bankAccount = bankAccount;
    }

    @Override
    public BalanceDTO getBalance(GetWalletBalanceDTO dto) throws DomainException {
        User user = userRepository.get(dto.getUserID());
        Wallet wallet = walletRepository.get(user.getWalletID());
        BankAccount account = bankAccount.get(user.getNumberAccount().getNumberAccount());
        BalanceDTO balanceDTO = new BalanceDTO(account.getMoney(), wallet.getCryptos());
        return balanceDTO;
    }
    
}
