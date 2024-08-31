package com.globant.application.repositories;

import com.globant.domain.exceptions.RepositoryConnectionException;
import com.globant.domain.exchange.Exchange;
import com.globant.domain.util.Serializer;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author erillope
 */
public class SerExchangeInstance implements ExchangeInstance, Serializable{
    private static SerExchangeInstance instance = null;
    private final static String source = "src\\main\\resources\\serializables\\exchange.ser";
    private Exchange exchangeInstance;
    
    private SerExchangeInstance() {
        this.exchangeInstance = Exchange.getInstance();
    }

    @Override
    public Exchange get() {
        return exchangeInstance;
    }

    @Override
    public void save(Exchange instance) throws RepositoryConnectionException {
        exchangeInstance = instance;
        commit();
    }
    
    protected void commit() throws RepositoryConnectionException{
        try{Serializer.serialize(this, source);}
        catch(IOException e){throw RepositoryConnectionException.failedConnection();}
    }
    
    public static SerExchangeInstance getInstance(){
        if (instance != null){return instance;}
        try{instance = (SerExchangeInstance)Serializer.desSerialize(source);}
        catch(Exception e){
            instance = new SerExchangeInstance();}
        return instance;
    }
    
}
