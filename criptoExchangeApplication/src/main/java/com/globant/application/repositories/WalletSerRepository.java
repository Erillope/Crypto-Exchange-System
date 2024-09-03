package com.globant.application.repositories;

import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.application.repositories.exceptions.KeyNotFoundException;
import com.globant.application.repositories.exceptions.WalletNotFoundException;
import com.globant.domain.util.Serializer;

/**
 *
 * @author erillope
 */
public class WalletSerRepository extends SerRepository<WalletID, Wallet>{
    private static WalletSerRepository instance = null;
    private final static String source = "src\\main\\resources\\serializables\\walletRepo.ser";
    
    private WalletSerRepository() {
        super(source);
    }
    
    public static WalletSerRepository getInstance(){
        if (instance != null){return instance;}
        try{instance = (WalletSerRepository)Serializer.desSerialize(source);}
        catch(Exception e){instance = new WalletSerRepository();}
        return instance;
    }

    @Override
    protected KeyNotFoundException throwNotFoundException() {
        return WalletNotFoundException.walletNotFound();
    }
    
}
