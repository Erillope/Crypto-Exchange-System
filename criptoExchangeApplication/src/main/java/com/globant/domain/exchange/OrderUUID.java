package com.globant.domain.exchange;

import java.util.UUID;

/**
 *
 * @author erillope
 */
public class OrderUUID extends OrderID{

    @Override
    protected String generate() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
    
}
