package com.globant.application.services.wallet;

import com.globant.application.config.Cache;
import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.accounts.BankAccount;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceUseCaseImpl implements GetWalletBalanceUseCase{
    @Override
    public BalanceDTO getBalance(GetWalletBalanceDTO dto) throws DomainException {
        Wallet wallet = Cache.getGlobalCacheInstance().currentUserWallet;
        BankAccount account = Cache.getGlobalCacheInstance().currentUserBankAccount;
        BalanceDTO balanceDTO = new BalanceDTO(account.getMoney(), wallet.getCryptos());
        return balanceDTO;
    }
    
}
