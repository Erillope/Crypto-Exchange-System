package com.globant.application.repositories;

import com.globant.domain.exceptions.RepositoryConnectionException;
import com.globant.domain.exchange.Exchange;

/**
 *
 * @author erillope
 */
public interface ExchangeInstance {
    public Exchange get();
    
    public void save(Exchange instance) throws RepositoryConnectionException;
}
