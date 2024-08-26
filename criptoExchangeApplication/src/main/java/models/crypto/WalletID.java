package models.crypto;

/**
 *
 * @author erillope
 */
public abstract class WalletID {
    private final String id;
    
    public WalletID(){
        this.id = generate();
    }
    
    public String getID(){return id;}
    
    protected abstract String generate();
}
