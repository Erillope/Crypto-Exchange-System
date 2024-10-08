package com.globant.domain.user.accounts;

import com.globant.domain.exceptions.InvalidEmailException;
import com.globant.domain.exceptions.InvalidPasswordException;
import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.util.PasswordVerificator;
import java.util.regex.Pattern;

/**
 *
 * @author erillope
 */
public class GmailAccount extends UserAccount{

    public GmailAccount(String name, String email, String password) throws InvalidUserAccountException {
        super(name, email, password);
    }

    @Override
    protected void verifyEmail() throws InvalidEmailException {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        boolean isValid = Pattern.compile(emailRegex).matcher(getEmail()).matches();
        if (!isValid){throw InvalidEmailException.invalidEmail();}
    }

    @Override
    protected void verifyPassword() throws InvalidPasswordException {
        boolean isValid = PasswordVerificator.verify(getPassword());
        if (!isValid){throw InvalidPasswordException.invalidException();}
    }
    
}
