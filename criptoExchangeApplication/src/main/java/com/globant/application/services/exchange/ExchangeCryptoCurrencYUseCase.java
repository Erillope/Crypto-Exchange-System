package com.globant.application.services.exchange;

import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface ExchangeCryptoCurrencyUseCase {
    public void exchange(ExchangeCryptoCurrencyDTO dto) throws DomainException;
}
