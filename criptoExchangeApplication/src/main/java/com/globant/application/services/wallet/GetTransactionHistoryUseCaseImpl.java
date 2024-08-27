package com.globant.application.services.wallet;

import com.globant.application.dto.GetTransactionHistoryDTO;
import com.globant.application.dto.TransactionHistoryDTO;
import com.globant.application.repositories.Repository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class GetTransactionHistoryUseCaseImpl implements GetTransactionHistoryUseCase{
    private final Repository<UserID, TransactionHistory> transactionHistoryRepository;

    public GetTransactionHistoryUseCaseImpl(Repository<UserID, TransactionHistory> transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }
    
    @Override
    public TransactionHistoryDTO getHistory(GetTransactionHistoryDTO dto) throws DomainException{
        TransactionHistory history = transactionHistoryRepository.get(dto.getUserID());
        TransactionHistoryDTO historyDTO = new TransactionHistoryDTO(history.getTransaction());
        return historyDTO;
    }
    
}
