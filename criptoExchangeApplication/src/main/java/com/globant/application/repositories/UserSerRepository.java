package com.globant.application.repositories;

import com.globant.domain.exceptions.KeyNotFoundException;
import com.globant.domain.exceptions.RepositoryConnectionException;
import com.globant.domain.user.User;
import com.globant.domain.user.UserID;
import com.globant.domain.util.Serializer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class UserSerRepository extends SerRepository<UserID,User> implements UserRepository{
    private static UserSerRepository instance = null;
    private final static String source = "src\\main\\resources\\com\\globant\\application\\serializables\\userRepo.ser";
    private final Map<String, User> dataByEmail;
    
    private UserSerRepository() {
        super(source);
        dataByEmail = new HashMap<>();
    }
    
    @Override
    public void save(UserID key, User value) throws RepositoryConnectionException{
        data.put(key, value);
        dataByEmail.put(value.getUserAccount().getEmail(), value);
        commit();
    }

    @Override
    public User getByEmail(String email) throws KeyNotFoundException{
        if (!containEmail(email)){throw KeyNotFoundException.keyNotFound();}
        return dataByEmail.get(email);
    }

    @Override
    public boolean containEmail(String email) {
        return dataByEmail.containsKey(email);
    }
    
    public static UserSerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (UserSerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new UserSerRepository();}
        return instance;
    }
}
