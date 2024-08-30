package com.globant.application.dto;

import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class GetTransactionHistoryDTO {
    private final UserID userID;
    
    public GetTransactionHistoryDTO(UserID userID){
        this.userID = userID;
    }
    
    public UserID getUserID(){return userID;}
}
