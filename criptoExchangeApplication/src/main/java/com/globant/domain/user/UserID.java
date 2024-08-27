package com.globant.domain.user;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author erillope
 */
public abstract class UserID implements Serializable{
    private final String id;
    
    public UserID(){
        this.id = generate();
    }
    
    public String getID(){return id;}
    
    protected abstract String generate();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final UserID other = (UserID) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
