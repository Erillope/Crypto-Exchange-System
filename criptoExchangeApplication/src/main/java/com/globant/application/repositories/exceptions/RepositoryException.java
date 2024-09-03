package com.globant.application.repositories.exceptions;

import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public class RepositoryException extends DomainException{
    public RepositoryException(String msg) {
        super(msg);
    }
}
