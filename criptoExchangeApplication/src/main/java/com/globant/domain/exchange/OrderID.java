package com.globant.domain.exchange;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author erillope
 */
public abstract class OrderID implements Serializable{
    private final String id;
    
    public OrderID(){
        this.id = generate();
    }
    
    public String getID(){return id;}
    
    protected abstract String generate();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderID other = (OrderID) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
