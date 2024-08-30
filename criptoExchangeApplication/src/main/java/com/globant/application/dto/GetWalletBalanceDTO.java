package com.globant.application.dto;

import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public class GetWalletBalanceDTO {
    private final UserID userID;
    
    public GetWalletBalanceDTO(UserID userID){
        this.userID = userID;
    }
    
    public UserID getUserID(){return userID;}
}
