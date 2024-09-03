package com.globant.application.services.wallet;

import com.globant.application.config.Cache;
import com.globant.application.dto.GetTransactionHistoryDTO;
import com.globant.application.dto.TransactionHistoryDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.TransactionHistory;

/**
 *
 * @author erillope
 */
public class GetTransactionHistoryUseCaseImpl implements GetTransactionHistoryUseCase{    
    @Override
    public TransactionHistoryDTO getHistory(GetTransactionHistoryDTO dto) throws DomainException{
        TransactionHistory history = Cache.getGlobalCacheInstance().currentUserTransactionHistory;
        TransactionHistoryDTO historyDTO = new TransactionHistoryDTO(history.getTransaction());
        return historyDTO;
    }
    
}
