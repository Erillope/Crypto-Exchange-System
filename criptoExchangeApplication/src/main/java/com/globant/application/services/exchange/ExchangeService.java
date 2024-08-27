package com.globant.application.services.exchange;

import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public class ExchangeService implements ExchangeCryptoCurrencyUseCase, PlaceBuyOrderUseCase, PlaceSaleOrderUseCase{
    private ExchangeCryptoCurrencyUseCase exchangeUseCase;
    private PlaceBuyOrderUseCase placeBuyOrderUseCase;
    private PlaceSaleOrderUseCase placeSaleOrderUseCase;

    public ExchangeService(ExchangeCryptoCurrencyUseCase exchangeUseCase, PlaceBuyOrderUseCase placeBuyOrderUseCase, PlaceSaleOrderUseCase placeSaleOrderUseCase) {
        this.exchangeUseCase = exchangeUseCase;
        this.placeBuyOrderUseCase = placeBuyOrderUseCase;
        this.placeSaleOrderUseCase = placeSaleOrderUseCase;
    }

    @Override
    public void exchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        this.exchangeUseCase.exchange(dto);
    }

    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) {
        this.placeBuyOrderUseCase.placeBuyOrder(dto);
    }

    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) {
        this.placeSaleOrderUseCase.placeSaleOrder(dto);
    }
    
}
