package com.globant.application.exceptions;

import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public class ApplicationException extends DomainException{
    public ApplicationException(String msg) {
        super(msg);
    }
}
