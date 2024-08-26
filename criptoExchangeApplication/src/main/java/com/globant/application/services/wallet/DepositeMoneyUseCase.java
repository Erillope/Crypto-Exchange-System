package com.globant.application.services.wallet;

import com.globant.application.dto.DepositeMoneyDTO;

/**
 *
 * @author erillope
 */
public interface DepositeMoneyUseCase {
    public void depositeMoney(DepositeMoneyDTO dto);
}
