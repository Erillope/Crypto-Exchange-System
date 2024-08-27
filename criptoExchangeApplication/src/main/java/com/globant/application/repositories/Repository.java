package com.globant.application.repositories;

import com.globant.domain.exceptions.KeyNotFoundException;
import com.globant.domain.exceptions.RepositoryConnectionException;

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
