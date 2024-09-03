package unitTests;

import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.accounts.NumberAccount;

/**
 *
 * @author erillope
 */
public class BankAccountText {
    private static void verifyValidNumberAccountTest() throws Exception{
        try{
            NumberAccount numberAccount = new NumberAccount("123456789");
            System.out.println("verifyValidNumberAccountTest Success");
        }
        catch(InvalidNumberAccountException e){throw new Exception("verifyValidNumberAccountTest Failed");}
    }
    
    private static void verifyInvalidNumberAccountTest() throws Exception{
        try{
            NumberAccount numberAccount = new NumberAccount("12345678A");
            throw new Exception("verifyInvalidNumberAccountTest Failed");
        }
        catch(InvalidNumberAccountException e){System.out.println("verifyInvalidNumberAccountTest Success");}
    }
    
    public static void main(String[] args) throws Exception{
        verifyValidNumberAccountTest();
        verifyInvalidNumberAccountTest();
    }
}
