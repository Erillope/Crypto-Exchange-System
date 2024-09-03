package com.globant.application.repositories;

import com.globant.application.repositories.exceptions.BankAccountNotFoundException;
import com.globant.application.repositories.exceptions.KeyNotFoundException;
import com.globant.domain.user.accounts.BankAccount;
import com.globant.domain.util.Serializer;

/**
 *
 * @author erillope
 */
public class BankAccountSerRepository extends SerRepository<String, BankAccount>{
    private static BankAccountSerRepository instance = null;
    private final static String source = "src\\main\\resources\\serializables\\bankRepo.ser";
    
    private BankAccountSerRepository() {
        super(source);
    }
    
    public static BankAccountSerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (BankAccountSerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new BankAccountSerRepository();}
        return instance;
    }

    @Override
    protected KeyNotFoundException throwNotFoundException() {
        return BankAccountNotFoundException.bankAccountNotFound();
    }
}
