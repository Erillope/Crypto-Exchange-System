package com.globant.domain.exchange;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class Transaction implements Serializable{
    private final Order order;
    private final TransactionType type;
    private final BigDecimal totalPrice;
    
    public Transaction(Order order, TransactionType type){
        this.order = order;
        this.type = type;
        this.totalPrice = calculateTotalPrice();
    }
    
    public TransactionType getType(){return type;}
    
    public BigDecimal getTotalPrice(){return totalPrice;}
    
    public Order getOrder(){return order;}
    
    private BigDecimal calculateTotalPrice(){
        return null;
    }
}
