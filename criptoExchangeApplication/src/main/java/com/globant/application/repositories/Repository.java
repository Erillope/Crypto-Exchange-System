package com.globant.application.repositories;

import com.globant.application.repositories.exceptions.KeyNotFoundException;
import com.globant.application.repositories.exceptions.RepositoryConnectionException;

/**
 *
 * @author erillope
 * @param <K>
 * @param <V>
 */
public interface Repository<K,V> {
    public V get(K key) throws KeyNotFoundException;
    
    public void save(K key, V value) throws RepositoryConnectionException;
    
    public boolean contain(K key);
    
}
