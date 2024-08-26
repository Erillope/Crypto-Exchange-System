package com.globant.domain.user;

/**
 *
 * @author erillope
 */
public abstract class UserAccount {
    private final String name;
    private final String email;
    private final String password;
    
    public UserAccount(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    protected abstract void verifyEmail() throws InvalidEmailException;
    
    protected abstract void verifyPassword() throws InvalidPasswordException;
    
    public String getName(){return name;}
    
    public String getEmail(){return email;}
    
    public String getPassword(){return password;}
}
