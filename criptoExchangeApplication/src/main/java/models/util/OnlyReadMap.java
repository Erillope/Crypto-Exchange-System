package models.util;

/**
 *
 * @author USER
 * @param <K>
 * @param <V>
 */
public interface OnlyReadMap<K,V> {
    public V get(K key);
}
