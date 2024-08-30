package integrationTests;

import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.application.dto.SignInDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.wallet.WalletService;
import java.math.BigDecimal;

/**
 *
 * @author eriilope
 */
public class DepositeMoneyTest {
    private static void depositeTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            WalletService walletService = builder.buildWalletService();
            authenticationService.signIn(new SignInDTO("erillope@gmail.com", "Erillope123"));
            
            GetWalletBalanceDTO getWalletBalanceDTO = new GetWalletBalanceDTO(authenticationService.getSignedUserDTO().getId());
            BalanceDTO balanceDTO = walletService.getBalance(getWalletBalanceDTO);
            
            DepositeMoneyDTO dto = new DepositeMoneyDTO(BigDecimal.TEN, authenticationService.getSignedUserDTO().getId());
            walletService.depositeMoney(dto);
            
            BalanceDTO newBalanceDTO = walletService.getBalance(getWalletBalanceDTO);
            BigDecimal newAmount = balanceDTO.getMoney().add(BigDecimal.TEN);
            boolean isValid = newAmount.equals(newBalanceDTO.getMoney());
            if (isValid){System.out.println("verifyValidAccountTest Success");}
            else{throw new Exception("");}
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    public static void main(String[] args) throws Exception{
        depositeTest();
    }
}
