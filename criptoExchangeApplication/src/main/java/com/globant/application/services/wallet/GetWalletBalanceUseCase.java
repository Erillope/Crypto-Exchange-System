package com.globant.application.services.wallet;

import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;

/**
 *
 * @author erillope
 */
public interface GetWalletBalanceUseCase {
    public BalanceDTO getBalance(GetWalletBalanceDTO dto);
}
