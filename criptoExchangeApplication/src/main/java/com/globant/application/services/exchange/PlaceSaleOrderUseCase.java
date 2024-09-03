package com.globant.application.services.exchange;

import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface PlaceSaleOrderUseCase {
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException;
}
