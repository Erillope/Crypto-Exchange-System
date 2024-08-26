package com.globant.domain.user;

import java.util.UUID;

/**
 *
 * @author erillope
 */
public class UserUUID extends UserID{

    @Override
    protected String generate() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
    
}