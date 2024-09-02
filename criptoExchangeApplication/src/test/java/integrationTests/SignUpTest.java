package integrationTests;

import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.SignUpDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.domain.factories.BankName;

/**
 *
 * @author erillope
 */
public class SignUpTest{
    private static void successSignUpTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            //SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "795763089", BankName.PACIFICO);
            //authenticationService.signUp(dto);
            for (int i=0; i<10; i++){
                SignUpDTO dto = new SignUpDTO("user"+i, "user"+i+"@gmail.com", "Password123", "89576308"+i, BankName.PICHINCHA);
                authenticationService.signUp(dto);
            }
            //UserRepository userRepository = UserSerRepository.getInstance();
            //if (userRepository.containEmail("erillope@gmail.com")){System.out.println("verifyValidAccountTest Success");}
            //else{throw new Exception("");}
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    private static void failedSignUpTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            SignUpDTO dto = new SignUpDTO("E", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            authenticationService.signUp(dto);
            System.out.println("verifyValidAccountTest Failed");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");e.printStackTrace();}
    }
    
    private static void alreadyExistUserTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            SignUpDTO dto = new SignUpDTO("Erick", "erillope@gmail.com", "Erillope123", "895763089", BankName.PACIFICO);
            authenticationService.signUp(dto);
            
            authenticationService.signUp(dto);
            System.out.println("verifyValidAccountTest Failed");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");e.printStackTrace();}
    }
    
    public static void main(String[] args) throws Exception{
        successSignUpTest();
    }
}
