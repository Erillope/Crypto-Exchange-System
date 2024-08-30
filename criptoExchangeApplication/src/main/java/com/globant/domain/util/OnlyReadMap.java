package com.globant.domain.util;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author USER
 * @param <K>
 * @param <V>
 */
public interface OnlyReadMap<K,V> {
    public V get(K key);
    
    public Set<Map.Entry<K,V>> entrySet();
}
