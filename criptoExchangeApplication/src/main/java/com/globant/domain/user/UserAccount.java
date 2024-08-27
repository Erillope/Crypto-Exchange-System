package com.globant.domain.user;

import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.exceptions.InvalidPasswordException;
import com.globant.domain.exceptions.InvalidEmailException;
import com.globant.domain.exceptions.InvalidUserNameException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author erillope
 */
public abstract class UserAccount implements Serializable{
    private final String name;
    private final String email;
    private final String password;
    
    public UserAccount(String name, String email, String password) throws InvalidUserAccountException{
        this.name = name;
        this.email = email;
        this.password = password;
        verifyAccount();
    }
    
    private void verifyAccount() throws InvalidUserAccountException{
        verifyName();
        verifyEmail();
        verifyPassword();
    }
    
    private void verifyName() throws InvalidUserNameException{
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{3,14}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean isValid = matcher.matches();
        if (!isValid){throw InvalidUserNameException.invalidName();}
    }
    
    protected abstract void verifyEmail() throws InvalidEmailException;
    
    protected abstract void verifyPassword() throws InvalidPasswordException;
    
    public String getName(){return name;}
    
    public String getEmail(){return email;}
    
    public String getPassword(){return password;}
}
