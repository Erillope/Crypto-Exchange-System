package com.globant.domain.util;

import com.globant.domain.exceptions.InvalidEmailException;

/**
 *
 * @author erillope
 */
public class EmailDomainExtractor {
    public static String extract(String email) throws InvalidEmailException{
        if (email.contains("@") && email.contains(".")){
            int start = email.indexOf('@');
            int end = email.lastIndexOf('.');
            return email.substring(start+1, end);
        }
        else {throw InvalidEmailException.invalidEmail();}
        
    }
}
