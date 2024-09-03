package com.globant.application.services.exchange;

import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface PlaceBuyOrderUseCase {
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException;
}
