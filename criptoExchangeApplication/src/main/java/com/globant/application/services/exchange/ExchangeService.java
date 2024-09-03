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
public class ExchangeService implements ExchangeCryptoCurrencyUseCase, PlaceSaleOrderUseCase, PlaceBuyOrderUseCase{
    private final ExchangeCryptoCurrencyUseCase exchangeUseCase;
    private final PlaceSaleOrderUseCase placeSaleOrderUseCase;
    private final PlaceBuyOrderUseCase placeBuyOrderUseCase;

    public ExchangeService(ExchangeCryptoCurrencyUseCase exchangeUseCase, PlaceSaleOrderUseCase placeSaleOrderUseCase, PlaceBuyOrderUseCase placeBuyOrderUseCase) {
        this.exchangeUseCase = exchangeUseCase;
        this.placeSaleOrderUseCase = placeSaleOrderUseCase;
        this.placeBuyOrderUseCase = placeBuyOrderUseCase;
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
        this.placeBuyOrderUseCase.placeBuyOrder(dto);
    }

    @Override
    public void placeSaleOrder(PlaceSaleOrderDTO dto) throws DomainException{
        this.placeSaleOrderUseCase.placeSaleOrder(dto);
    }
    
}
