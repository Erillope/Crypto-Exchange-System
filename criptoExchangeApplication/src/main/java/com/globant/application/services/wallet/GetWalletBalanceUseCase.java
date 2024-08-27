package com.globant.application.services.wallet;

import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface GetWalletBalanceUseCase {
    public BalanceDTO getBalance(GetWalletBalanceDTO dto) throws DomainException;
}
