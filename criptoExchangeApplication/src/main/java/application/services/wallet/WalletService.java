package application.services.wallet;

import application.dto.BalanceDTO;
import application.dto.DepositeMoneyDTO;
import application.dto.GetTransactionHistoryDTO;
import application.dto.GetWalletBalanceDTO;
import application.dto.TransactionHistoryDTO;

/**
 *
 * @author erillope
 */
public class WalletService implements DepositeMoneyUserCase, GetTransactionHistoryUserCase, GetWalletBalanceUserCase{
    private final DepositeMoneyUserCase depositeUserCase;
    private final GetTransactionHistoryUserCase getHistoryUserCase;
    private final GetWalletBalanceUserCase getBalanceUserCase;

    public WalletService(DepositeMoneyUserCase depositeUserCase, 
            GetTransactionHistoryUserCase getHistoryUserCase, GetWalletBalanceUserCase getBalanceUserCase) {
        this.depositeUserCase = depositeUserCase;
        this.getHistoryUserCase = getHistoryUserCase;
        this.getBalanceUserCase = getBalanceUserCase;
    }

    @Override
    public void depositeMoney(DepositeMoneyDTO dto) {
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
