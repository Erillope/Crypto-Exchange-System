package models.user;

/**
 *
 * @author erillope
 */
public class InvalidEmailException extends Exception{
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
