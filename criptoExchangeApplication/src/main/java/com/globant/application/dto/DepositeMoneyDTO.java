package com.globant.application.dto;

import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class DepositeMoneyDTO {
    private final BigDecimal amount;
    private final String userID;
    
    public DepositeMoneyDTO(BigDecimal amount, String userID) {
        this.amount = amount;
        this.userID = userID;
    }
    
    public BigDecimal getAmount(){return amount;}
    
    public String getUserID(){return userID;}
}
