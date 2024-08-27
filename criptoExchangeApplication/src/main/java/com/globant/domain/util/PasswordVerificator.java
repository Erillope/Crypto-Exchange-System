package com.globant.domain.util;

import java.util.regex.Pattern;

/**
 *
 * @author erillope
 */
public class PasswordVerificator {
    public static boolean verify(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        boolean isValid = Pattern.compile(passwordRegex).matcher(password).matches();
        return isValid;
    }
}
