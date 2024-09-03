package com.globant.application.repositories.exceptions;

/**
 *
 * @author erillope
 */
public class RepositoryConnectionException extends RepositoryException{
    public RepositoryConnectionException(String msg) {
        super(msg);
    }
    
    public static RepositoryConnectionException failedConnection(){
        return new RepositoryConnectionException("CONNECTION TO REPOSITORY FAILED");
    }
}
