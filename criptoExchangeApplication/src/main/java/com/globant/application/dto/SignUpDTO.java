package com.globant.application.dto;

import com.globant.domain.factories.BankName;

/**
 *
 * @author erillope
 */
public class SignUpDTO {
    private final String name;
    private final String email;
    private final String password;
    private final String numberAccount;
    private final BankName bankName;
    
    public SignUpDTO(String name, String email, String password, String numberAccount, BankName bankName){
        this.numberAccount = numberAccount;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bankName = bankName;
    }
    
    public String getName(){return name;}
    
    public String getEmail(){return email;}
    
    public String getPassword(){return password;}
    
    public String getNumberAccount(){return numberAccount;}
    
    public BankName getBankName(){return bankName;}
}
