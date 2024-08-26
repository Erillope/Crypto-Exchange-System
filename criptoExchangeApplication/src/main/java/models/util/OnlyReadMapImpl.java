package models.util;

import java.util.Map;

/**
 *
 * @author erillope
 * @param <K>
 * @param <V>
 */
public class OnlyReadMapImpl<K,V> implements OnlyReadMap<K,V>{
    private final Map<K,V> map;
    
    public OnlyReadMapImpl(Map<K,V> map){
        this.map = map;
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }
    
    
}
