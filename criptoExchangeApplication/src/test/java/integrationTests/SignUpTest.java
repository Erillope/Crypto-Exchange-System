package integrationTests;

import com.globant.application.dto.SignUpDTO;
import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.application.services.authentication.SignUpUseCase;
import com.globant.application.services.authentication.SignUpUseCaseImpl;
import com.globant.domain.factories.BankName;
import com.globant.domain.user.BankAccount;

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
    
    public static void main(String[] args) throws Exception{
        successSignUpTest();
    }
}
