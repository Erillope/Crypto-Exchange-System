package com.globant.application.repositories;

import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.util.Serializer;

/**
 *
 * @author erillope
 */
public class WalletSerRepository extends SerRepository<WalletID, Wallet>{
    private static WalletSerRepository instance = null;
    private final static String source = "src\\main\\resources\\com\\globant\\application\\serializables\\walletRepo.ser";
    
    private WalletSerRepository() {
        super(source);
    }
    
    public static WalletSerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (WalletSerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new WalletSerRepository();}
        return instance;
    }
}
