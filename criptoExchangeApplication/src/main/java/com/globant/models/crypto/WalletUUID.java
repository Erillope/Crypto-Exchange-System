package models.crypto;

import java.util.UUID;

/**
 *
 * @author erillope
 */
public class WalletUUID extends WalletID{

    @Override
    protected String generate() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
    
}
