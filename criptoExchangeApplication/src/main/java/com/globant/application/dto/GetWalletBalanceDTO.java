package com.globant.application.dto;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceDTO {
    private final String userID;
    
    public GetWalletBalanceDTO(String userID){
        this.userID = userID;
    }
    
    public String getUserID(){return userID;}
}
