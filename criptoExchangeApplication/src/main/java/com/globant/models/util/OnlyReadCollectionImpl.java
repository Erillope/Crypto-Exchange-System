package models.util;

import java.util.List;

/**
 *
 * @author erillope
 */
public class OnlyReadCollectionImpl<T> implements OnlyReadCollection<T>{
    private final List<T> collection;
    
    public OnlyReadCollectionImpl(List<T> collection){
        this.collection = collection;
    }

    @Override
    public T get(int index){
        return collection.get(index);
    }
    
}
