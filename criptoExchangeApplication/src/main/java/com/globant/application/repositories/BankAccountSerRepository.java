package com.globant.application.repositories;

import com.globant.domain.user.BankAccount;
import com.globant.domain.util.Serializer;

/**
 *
 * @author erillope
 */
public class BankAccountSerRepository extends SerRepository<String, BankAccount>{
    private static BankAccountSerRepository instance = null;
    private final static String source = "src\\main\\resources\\com\\globant\\application\\serializables\\bankRepo.ser";
    
    private BankAccountSerRepository() {
        super(source);
    }
    
    public static BankAccountSerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (BankAccountSerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new BankAccountSerRepository();}
        return instance;
    }
}
