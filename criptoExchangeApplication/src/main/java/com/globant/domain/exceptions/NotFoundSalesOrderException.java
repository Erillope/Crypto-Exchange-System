package com.globant.domain.exceptions;

/**
 *
 * @author erillope
 */
public class NotFoundSalesOrderException extends ApplicationException{
    public NotFoundSalesOrderException(String msg) {
        super(msg);
    }
    
    public static NotFoundSalesOrderException notFound()
    {return new NotFoundSalesOrderException("NOT FOUND SALES ORDER");}
    
}
