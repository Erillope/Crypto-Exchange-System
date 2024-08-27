package com.globant.application.dto;

import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class GetTransactionHistoryDTO {
    private UserID userID;
    
    public GetTransactionHistoryDTO(){
    }
    
    public UserID getUserID(){return userID;}
    
    public void setUserID(UserID id){userID = id;}
}
