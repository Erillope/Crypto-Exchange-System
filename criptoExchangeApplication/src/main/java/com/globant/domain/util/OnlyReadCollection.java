package com.globant.domain.util;

/**
 *
 * @author erillope
 */
public interface OnlyReadCollection<T> {
    public T get(int index);
    
    public boolean isEmpty();
    
    public int size();
}
