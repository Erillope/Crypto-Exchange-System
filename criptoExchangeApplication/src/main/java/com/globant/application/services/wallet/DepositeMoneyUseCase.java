package application.services.wallet;

import application.dto.DepositeMoneyDTO;

/**
 *
 * @author erillope
 */
public interface DepositeMoneyUseCase {
    public void depositeMoney(DepositeMoneyDTO dto);
}
