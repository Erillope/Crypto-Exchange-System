package com.globant.domain.user;

/**
 *
 * @author erillope
 */
public abstract class UserID {
    private final String id;
    
    public UserID(){
        this.id = generate();
    }
    
    public String getID(){return id;}
    
    protected abstract String generate();
}
