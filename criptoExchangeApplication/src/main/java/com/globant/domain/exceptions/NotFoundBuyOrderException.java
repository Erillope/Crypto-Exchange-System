package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class NotFoundBuyOrderException extends ApplicationException{
    public NotFoundBuyOrderException(String msg) {
        super(msg);
    }
    
    public static NotFoundBuyOrderException notFound(){
        return new NotFoundBuyOrderException("NOT FOUND BUY ORDER");
    }
}
