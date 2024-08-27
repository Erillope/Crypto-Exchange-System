package com.globant.application.dto;

import com.globant.domain.user.UserID;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class DepositeMoneyDTO {
    private final BigDecimal amount;
    private UserID userID;
    
    public DepositeMoneyDTO(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getAmount(){return amount;}
    
    public UserID getUserID(){return userID;}
    
    public void setUserID(UserID id){userID = id;}
}
