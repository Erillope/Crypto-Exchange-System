package com.globant.application.services.wallet;

import com.globant.application.dto.GetTransactionHistoryDTO;
import com.globant.application.dto.TransactionHistoryDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface GetTransactionHistoryUseCase {
    public TransactionHistoryDTO getHistory(GetTransactionHistoryDTO dto) throws DomainException;
}
