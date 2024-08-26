package com.globant.application.dto;

/**
 *
 * @author erillope
 */
public class GetTransactionHistoryDTO {
    private final String userID;
    
    public GetTransactionHistoryDTO(String userID){
        this.userID = userID;
    }
    
    public String getUserID(){return userID;}
}
