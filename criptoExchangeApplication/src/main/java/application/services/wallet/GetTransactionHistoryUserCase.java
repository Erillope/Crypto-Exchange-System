package application.services.wallet;

import application.dto.GetTransactionHistoryDTO;
import application.dto.TransactionHistoryDTO;

/**
 *
 * @author erillope
 */
public interface GetTransactionHistoryUserCase {
    public TransactionHistoryDTO getHistory(GetTransactionHistoryDTO dto);
}
