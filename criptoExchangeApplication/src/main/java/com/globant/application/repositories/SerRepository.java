package com.globant.application.repositories;

import com.globant.application.repositories.exceptions.KeyNotFoundException;
import com.globant.application.repositories.exceptions.RepositoryConnectionException;
import com.globant.domain.util.Serializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 * @param <K>
 * @param <V>
 */
public abstract class SerRepository<K extends Serializable,V extends Serializable> implements Repository<K,V>, Serializable{
    protected final String source;
    protected final Map<K,V> data;

    protected SerRepository(String source) {
        this.source = source;
        data = new HashMap<>();
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        if (!contain(key)){throw throwNotFoundException();}
        return data.get(key);
    }

    @Override
    public void save(K key, V value) throws RepositoryConnectionException{
        data.put(key, value);
        commit();
    }

    @Override
    public boolean contain(K key) {
        return data.containsKey(key);
    }
    
    protected void commit() throws RepositoryConnectionException{
        try{Serializer.serialize(this, source);}
        catch(IOException e){throw RepositoryConnectionException.failedConnection();}
    }
    
    protected abstract KeyNotFoundException throwNotFoundException();
}
