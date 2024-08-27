package com.globant.domain.crypto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author erillope
 */
public abstract class WalletID implements Serializable{
    private final String id;
    
    public WalletID(){
        this.id = generate();
    }
    
    public String getID(){return id;}
    
    protected abstract String generate();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final WalletID other = (WalletID) obj;
        return Objects.equals(this.id, other.id);
    }
}
