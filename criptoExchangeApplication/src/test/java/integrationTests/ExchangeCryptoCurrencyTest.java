package integrationTests;

import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.AvailableCoinsDTO;
import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.dto.SignInDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.domain.crypto.CryptoCurrencyName;
import java.math.BigDecimal;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyTest {
    private static void successExchangeTest() throws Exception{
        try{
            ServiceBuilder builder = new DefaultServiceBuilder();
            builder.buildInitializer().init();
            AuthenticationService authenticationService = builder.buildAuthenticationService();
            ExchangeService exchangeService = builder.buildExchangeService();
            authenticationService.signIn(new SignInDTO("erillope@gmail.com", "Erillope123"));
            AvailableCoinsDTO availableCoins = exchangeService.getAvailableCoins();
            BigDecimal bitcoinAmount = availableCoins.getCryptoCurrency().get(CryptoCurrencyName.BITCOIN).getAmount();
            
            ExchangeCryptoCurrencyDTO dto = new ExchangeCryptoCurrencyDTO(CryptoCurrencyName.BITCOIN, 
                    BigDecimal.ONE, authenticationService.getSignedUserDTO().getId());
            exchangeService.exchange(dto);
            
            availableCoins = exchangeService.getAvailableCoins();
            BigDecimal newBitcoinAmount = availableCoins.getCryptoCurrency().get(CryptoCurrencyName.BITCOIN).getAmount();

            boolean isValid = newBitcoinAmount.add(BigDecimal.ONE).equals(bitcoinAmount);
            if (isValid){System.out.println("verifyValidAccountTest Success");}
            else{throw new Exception("");}
            
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    public static void main(String[] args) throws Exception{
        successExchangeTest();
    }
}
