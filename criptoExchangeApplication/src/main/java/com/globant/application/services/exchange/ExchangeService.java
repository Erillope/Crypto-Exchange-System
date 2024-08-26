package com.globant.application.services.exchange;

import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;

/**
 *
 * @author erillope
 */
public class ExchangeService implements ExchangeCryptoCurrenceUseCase, PlaceBuyOrderUseCase, PlaceSaleOrderUseCase{
    private ExchangeCryptoCurrenceUseCase exchangeUseCase;
    private PlaceBuyOrderUseCase placeBuyOrderUseCase;
    private PlaceSaleOrderUseCase placeSaleOrderUseCase;

    public ExchangeService(ExchangeCryptoCurrenceUseCase exchangeUseCase, PlaceBuyOrderUseCase placeBuyOrderUseCase, PlaceSaleOrderUseCase placeSaleOrderUseCase) {
        this.exchangeUseCase = exchangeUseCase;
        this.placeBuyOrderUseCase = placeBuyOrderUseCase;
        this.placeSaleOrderUseCase = placeSaleOrderUseCase;
    }

    @Override
    public void exchange(ExchangeCryptoCurrencyDTO dto) {
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
