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
public class ExchangeService implements ExchangeCryptoCurrencyUseCase, PlaceBuyOrderUseCase, PlaceSaleOrderUseCase{
    private final ExchangeCryptoCurrencyUseCase exchangeUseCase;
    private final PlaceBuyOrderUseCase placeBuyOrderUseCase;
    private final PlaceSaleOrderUseCase placeSaleOrderUseCase;

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
