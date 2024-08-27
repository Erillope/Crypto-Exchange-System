package com.globant.domain.user;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author erillope
 */
public class NumberAccount implements Serializable{
    private final String numberAccount;
    
    public NumberAccount(String numberAccount) throws InvalidNumberAccountException{
        this.numberAccount = numberAccount;
        verifyNumberAccount();
    }
    
    public String getNumberAccount(){return numberAccount;}
    
    private void verifyNumberAccount() throws InvalidNumberAccountException{
        if (!numberAccount.matches("\\d+")){throw InvalidNumberAccountException.invalidNumberAccount();}
        if (numberAccount.length() < 8 || numberAccount.length() > 12){throw InvalidNumberAccountException.invalidNumberAccount();} 
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.numberAccount);
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
        final NumberAccount other = (NumberAccount) obj;
        return Objects.equals(this.numberAccount, other.numberAccount);
    }
}
