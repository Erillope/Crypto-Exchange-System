package integrationTests;

import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.SignInDTO;
import com.globant.application.dto.SignUpDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.domain.factories.BankName;

/**
 *
 * @author erillope
 */
public class SignInTest {
    private static void successSignInTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            authenticationService.signUp(dto);
            SignInDTO inDto = new SignInDTO("erillope@gmail.com", "Erillope123");
            authenticationService.signIn(inDto);
            
            if (authenticationService.getSignedUserDTO().getEmail().equals("erillope@gmail.com"))
            {System.out.println("verifyValidAccountTest Success");}
            else{throw new Exception("");}
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    private static void incorrectPasswordTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            authenticationService.signUp(dto);
            SignInDTO inDto = new SignInDTO("erillope@gmail.com", "Erillope1234");
            authenticationService.signIn(inDto);
            
            System.out.println("verifyValidAccountTest Failed");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");e.printStackTrace();}
    }
    
    public static void main(String[] args) throws Exception{
        incorrectPasswordTest();
    }
}
