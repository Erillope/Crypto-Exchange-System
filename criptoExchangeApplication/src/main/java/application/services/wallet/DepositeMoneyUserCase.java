package application.services.wallet;

import application.dto.DepositeMoneyDTO;

/**
 *
 * @author erillope
 */
public interface DepositeMoneyUserCase {
    public void depositeMoney(DepositeMoneyDTO dto);
}
