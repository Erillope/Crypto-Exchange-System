package integrationTests;

import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.crypto.WalletUUID;
import com.globant.domain.factories.UserFactory;
import com.globant.domain.user.accounts.GmailAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.accounts.UserAccount;

/**
 *
 * @author erillope
 */
public class RepositoryTest {
    private static void saveModelTest() throws Exception{
        UserFactory userFactory = new UserFactory();
        WalletID walletID = new WalletUUID();
        UserRepository userRepository = UserSerRepository.getInstance();
        UserAccount account = new GmailAccount("Erick", "erillope@gmail.com", "Erillope123");
        User user = userFactory.createUser("457698756", walletID, account);
        
        userRepository.save(user.getUserID(), user);
        boolean isValid = user.equals(userRepository.get(user.getUserID()));
        if (isValid){System.out.println("saveModelTest Success");}
        else {System.out.println("saveModelTest Failed");}
    }
    
    public static void main(String[] args) throws Exception{
        saveModelTest();
    }
}
