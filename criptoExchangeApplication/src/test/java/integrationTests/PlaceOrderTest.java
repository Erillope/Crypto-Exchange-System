package integrationTests;

import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.application.dto.SignInDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.domain.crypto.CryptoCurrencyName;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class PlaceOrderTest {
    private static void buyOrderTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            ExchangeService exchangeService = builder.buildExchangeService();
            authenticationService.signIn(new SignInDTO("user7@gmail.com", "Password123"));
            
            PlaceBuyOrderDTO dto = new PlaceBuyOrderDTO(CryptoCurrencyName.BITCOIN, new BigDecimal("17"), new BigDecimal("30"), authenticationService.getSignedUserDTO().getId());
            exchangeService.placeBuyOrder(dto);
            System.out.println("verifyValidAccountTest Success");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    private static void saleOrderTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            ExchangeService exchangeService = builder.buildExchangeService();
            authenticationService.signIn(new SignInDTO("user7@gmail.com", "Password123"));
            
            PlaceSaleOrderDTO dto = new PlaceSaleOrderDTO(CryptoCurrencyName.BITCOIN, new BigDecimal("17"), new BigDecimal("30"), authenticationService.getSignedUserDTO().getId());
            exchangeService.placeSaleOrder(dto);
            System.out.println("verifyValidAccountTest Success");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    public static void main(String[] args) throws Exception{
          saleOrderTest();
    }
}
