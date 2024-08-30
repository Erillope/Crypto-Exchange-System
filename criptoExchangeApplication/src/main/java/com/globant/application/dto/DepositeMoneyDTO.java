package com.globant.application.dto;

import com.globant.domain.user.UserID;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class DepositeMoneyDTO {
    private final BigDecimal amount;
    private final UserID userID;
    
    public DepositeMoneyDTO(BigDecimal amount, UserID userID) {
        this.amount = amount;
        this.userID = userID;
    }
    
    public BigDecimal getAmount(){return amount;}
    
    public UserID getUserID(){return userID;}
}
