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
