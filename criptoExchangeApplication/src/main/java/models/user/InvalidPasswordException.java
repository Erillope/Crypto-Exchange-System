package models.user;

/**
 *
 * @author erillope
 */
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
