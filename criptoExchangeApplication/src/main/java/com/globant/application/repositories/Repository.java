package application.repositories;

/**
 *
 * @author erillope
 * @param <K>
 * @param <V>
 */
public interface Repository<K,V> {
    public V get(K key) throws KeyNotFoundException;
    
    public void save(K key, V value);
    
    public boolean contain(K key);
    
}
