package com.globant.application.services.wallet;

import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface DepositeMoneyUseCase {
    public void depositeMoney(DepositeMoneyDTO dto) throws DomainException;
}
