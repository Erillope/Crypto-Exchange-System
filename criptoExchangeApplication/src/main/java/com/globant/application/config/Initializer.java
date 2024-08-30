package com.globant.application.config;

import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface Initializer{
    public void init() throws DomainException;
}
