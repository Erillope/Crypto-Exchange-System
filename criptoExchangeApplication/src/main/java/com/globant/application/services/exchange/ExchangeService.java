package com.globant.application.services.exchange;

import com.globant.application.dto.AvailableCoinsDTO;
import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public class ExchangeService implements ExchangeCryptoCurrencyUseCase, PlaceOrderUseCase{
    private final ExchangeCryptoCurrencyUseCase exchangeUseCase;
    private final PlaceOrderUseCase placeOrderUseCase;

    public ExchangeService(ExchangeCryptoCurrencyUseCase exchangeUseCase, PlaceOrderUseCase placeBuyOrderUseCase) {
        this.exchangeUseCase = exchangeUseCase;
        this.placeOrderUseCase = placeBuyOrderUseCase;
    }

    @Override
    public void exchange(ExchangeCryptoCurrencyDTO dto) throws DomainException{
        this.exchangeUseCase.exchange(dto);
    }
    
    @Override
    public AvailableCoinsDTO getAvailableCoins() throws DomainException {
        return this.exchangeUseCase.getAvailableCoins();
    }

    @Override
    public void placeBuyOrder(PlaceBuyOrderDTO dto) throws DomainException{
        this.placeOrderUseCase.placeBuyOrder(dto);
    }

    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException{
        this.placeOrderUseCase.placeSaleOrder(dto);
    }
    
}
