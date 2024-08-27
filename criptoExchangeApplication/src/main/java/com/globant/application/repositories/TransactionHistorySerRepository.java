package com.globant.application.repositories;

import com.globant.domain.exchange.TransactionHistory;
import com.globant.domain.user.UserID;
import com.globant.domain.util.Serializer;

/**
 *
 * @author erillope
 */
public class TransactionHistorySerRepository extends SerRepository<UserID, TransactionHistory>{
    private static TransactionHistorySerRepository instance = null;
    private final static String source = "src\\main\\resources\\com\\globant\\application\\serializables\\transactionHistoryRepo.ser";
    
    public TransactionHistorySerRepository() {
        super(source);
    }
    
    public static TransactionHistorySerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (TransactionHistorySerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new TransactionHistorySerRepository();}
        return instance;
    }
    
}
