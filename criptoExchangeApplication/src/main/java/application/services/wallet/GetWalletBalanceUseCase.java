package application.services.wallet;

import application.dto.BalanceDTO;
import application.dto.GetWalletBalanceDTO;

/**
 *
 * @author erillope
 */
public interface GetWalletBalanceUseCase {
    public BalanceDTO getBalance(GetWalletBalanceDTO dto);
}
