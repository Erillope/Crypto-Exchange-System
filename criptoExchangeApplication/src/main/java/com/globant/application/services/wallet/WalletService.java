package com.globant.application.services.wallet;

import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.application.dto.GetTransactionHistoryDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.application.dto.TransactionHistoryDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public class WalletService implements DepositeMoneyUseCase, GetTransactionHistoryUseCase, GetWalletBalanceUseCase{
    private final DepositeMoneyUseCase depositeUserCase;
    private final GetTransactionHistoryUseCase getHistoryUserCase;
    private final GetWalletBalanceUseCase getBalanceUserCase;

    public WalletService(DepositeMoneyUseCase depositeUserCase, 
            GetTransactionHistoryUseCase getHistoryUserCase, GetWalletBalanceUseCase getBalanceUserCase) {
        this.depositeUserCase = depositeUserCase;
        this.getHistoryUserCase = getHistoryUserCase;
        this.getBalanceUserCase = getBalanceUserCase;
    }

    @Override
    public void depositeMoney(DepositeMoneyDTO dto) throws DomainException{
        this.depositeUserCase.depositeMoney(dto);
    }

    @Override
    public TransactionHistoryDTO getHistory(GetTransactionHistoryDTO dto) {
        return this.getHistoryUserCase.getHistory(dto);
    }

    @Override
    public BalanceDTO getBalance(GetWalletBalanceDTO dto) {
        return this.getBalanceUserCase.getBalance(dto);
    }
}
