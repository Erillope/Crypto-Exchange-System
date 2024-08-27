package com.globant.application.repositories;

import com.globant.domain.exceptions.KeyNotFoundException;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;

/**
 *
 * @author USER
 */
public interface UserRepository extends Repository<UserID, User>{
    public User getByEmail(String email) throws KeyNotFoundException;
    
    public boolean containEmail(String email);
}
