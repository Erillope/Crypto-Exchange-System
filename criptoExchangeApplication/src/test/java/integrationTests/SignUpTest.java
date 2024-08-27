package integrationTests;

import com.globant.application.dto.SignUpDTO;
import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.application.services.authentication.SignUpUseCase;
import com.globant.application.services.authentication.SignUpUseCaseImpl;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.crypto.WalletUUID;
import com.globant.domain.factories.BankName;
import com.globant.domain.factories.UserFactory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.GmailAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserAccount;

/**
 *
 * @author erillope
 */
public class SignUpTest{
    private static void successSignUpTest() throws Exception{
        try{
            UserRepository userRepository = UserSerRepository.getInstance();
            Repository<String, BankAccount> bankAccountRepository = BankAccountSerRepository.getInstance();
            SignUpUseCase signUpUseCase = new SignUpUseCaseImpl(userRepository, bankAccountRepository);
            SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            signUpUseCase.signUp(dto);
            System.out.println("verifyValidAccountTest Success");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");}
    }
    
    private static void failedSignUpTest() throws Exception{
        try{
            UserRepository userRepository = UserSerRepository.getInstance();
            Repository<String, BankAccount> bankAccountRepository = BankAccountSerRepository.getInstance();
            SignUpUseCase signUpUseCase = new SignUpUseCaseImpl(userRepository, bankAccountRepository);
            SignUpDTO dto = new SignUpDTO("A", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            signUpUseCase.signUp(dto);
            System.out.println("verifyValidAccountTest Failed");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");}
    }
    
    private static void alreadyExistUserTest() throws Exception{
        try{
            UserRepository userRepository = UserSerRepository.getInstance();
            Repository<String, BankAccount> bankAccountRepository = BankAccountSerRepository.getInstance();
            UserFactory userFactory = new UserFactory();
            WalletID walletID = new WalletUUID();
            UserAccount account = new GmailAccount("Erick", "erillope@gmail.com", "Erillope123");
            User user = userFactory.createUser("457698756", walletID, account);
            userRepository.save(user.getUserID(), user);
            
            
            SignUpUseCase signUpUseCase = new SignUpUseCaseImpl(userRepository, bankAccountRepository);
            SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "457698756", BankName.PACIFICO);
            signUpUseCase.signUp(dto);
            System.out.println("verifyValidAccountTest Failed");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");}
    }
    
    public static void main(String[] args) throws Exception{
        
    }
}
