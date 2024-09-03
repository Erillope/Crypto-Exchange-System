package com.globant.application.repositories;

import com.globant.application.repositories.exceptions.RepositoryConnectionException;
import com.globant.domain.exchange.Exchange;

/**
 *
 * @author erillope
 */
public interface ExchangeInstance {
    public Exchange get();
    
    public void save(Exchange instance) throws RepositoryConnectionException;
}
