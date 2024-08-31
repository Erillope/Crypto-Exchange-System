package com.globant.application.services.wallet;

import com.globant.application.config.ApplicationCache;
import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.BankAccount;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceUseCaseImpl implements GetWalletBalanceUseCase{
    @Override
    public BalanceDTO getBalance(GetWalletBalanceDTO dto) throws DomainException {
        Wallet wallet = ApplicationCache.getInstance().currentUserWallet;
        BankAccount account = ApplicationCache.getInstance().currentUserBankAccount;
        BalanceDTO balanceDTO = new BalanceDTO(account.getMoney(), wallet.getCryptos());
        return balanceDTO;
    }
    
}
