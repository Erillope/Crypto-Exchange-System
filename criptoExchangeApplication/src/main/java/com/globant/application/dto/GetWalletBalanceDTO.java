package com.globant.application.dto;

import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceDTO {
    private UserID userID;
    
    public GetWalletBalanceDTO(){
    }
    
    public UserID getUserID(){return userID;}
    
    public void setUserID(UserID id){userID = id;}
}
