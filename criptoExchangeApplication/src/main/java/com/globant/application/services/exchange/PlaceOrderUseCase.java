package com.globant.application.services.exchange;

import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface PlaceOrderUseCase {
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException;
    
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException;
}
