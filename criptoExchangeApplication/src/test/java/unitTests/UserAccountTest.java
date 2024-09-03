package unitTests;

import com.globant.domain.exceptions.InvalidEmailException;
import com.globant.domain.exceptions.InvalidPasswordException;
import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.exceptions.InvalidUserNameException;
import com.globant.domain.user.accounts.GmailAccount;
import com.globant.domain.user.accounts.OutlookAccount;
import com.globant.domain.user.accounts.UserAccount;

/**
 *
 * @author erillope
 */
public class UserAccountTest {
    private static void verifyValidAccountTest() throws Exception{
        try{
            UserAccount gmailUserAccount = new GmailAccount("Erick", "erillope@gmail.com", "Erillope123");
            UserAccount outlookUserAccount = new OutlookAccount("Erick", "erillope@outlook.com", "Erillope123");
            System.out.println("verifyValidAccountTest Success");
        }
        catch(InvalidUserAccountException e){throw new Exception("verifyValidAccountTest Failed");}
    }
    
    private static void verifyInvalidAccountTest() throws Exception{
        try{
            UserAccount gmailUserAccount = new GmailAccount("Erick.", "erillope@gmail.com", "Erillope123");
            throw new Exception("verifyValidAccountTest Failed");
        }
        catch(InvalidUserNameException e){}
        try{
            UserAccount gmailUserAccount = new GmailAccount("Erick", "erillopegmail.com", "Erillope123");
            throw new Exception("verifyValidAccountTest Failed");
        }
        catch(InvalidEmailException e){}
        try{
            UserAccount gmailUserAccount = new GmailAccount("Erick", "erillope@gmail.com", "erillope123");
            throw new Exception("verifyValidAccountTest Failed");
        }
        catch(InvalidPasswordException e){System.out.println("verifyInvalidAccountTest Success");}
    }
        
    public static void main(String[] args) throws Exception{
        verifyValidAccountTest();
        verifyInvalidAccountTest();
    }
}
