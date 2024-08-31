package com.globant.application.config;

import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.UserID;

/**
 *
 * @author erillope
 */
public interface Cache {
    public void init(UserID userID) throws DomainException;
    
    public void clear();
}
