package integrationTests;

import com.globant.application.config.ApplicationCache;
import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.dto.PlaceBuyOrderDTO;
import com.globant.application.dto.PlaceSaleOrderDTO;
import com.globant.application.dto.SignInDTO;
import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.SerExchangeInstance;
import com.globant.application.repositories.WalletSerRepository;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.domain.crypto.CryptoCurrency;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.exchange.BuyOrder;
import com.globant.domain.exchange.SalesOrder;
import com.globant.domain.factories.CryptoCurrencyFactory;
import static com.globant.infrastructure.fxml.FxmlApp.authenticationService;
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
            System.out.println("user id: "+authenticationService.getSignedUserDTO().getId());
            
            //BuyOrder buyOrder = ApplicationCache.getInstance().exchange.buyOrderBook.get(0);
            //for (SalesOrder s: ApplicationCache.getInstance().exchange.salesOrderBook){
            //    System.out.println(s.getMinPrice()+ ", "+s.getUserID()+", "+s.getAmount()+ ", "+s+", "+s.getRemainigAmount());
            //}
            
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
            System.out.println("user id: "+authenticationService.getSignedUserDTO().getId());
            
            SalesOrder salesOrder = ApplicationCache.getInstance().exchange.salesOrderBook.get(0);
            for (BuyOrder b: ApplicationCache.getInstance().exchange.buyOrderBook){
                System.out.println(b.getMaxPrice()+ ", "+b.getUserID()+", "+b.getAmount()+ ", "+b+", "+b.getRemainigAmount());
            }
            
            //PlaceSaleOrderDTO dto = new PlaceSaleOrderDTO(CryptoCurrencyName.BITCOIN, new BigDecimal("17"), new BigDecimal("30"), authenticationService.getSignedUserDTO().getId());
            //exchangeService.placeSaleOrder(dto);
            //System.out.println("buy despues");
            //for (BuyOrder b: ApplicationCache.getInstance().exchange.buyOrderBook){
            //    System.out.println(b.getMaxPrice()+ ", "+b.getUserID()+", "+b.getAmount()+ ", "+b+", "+b.getRemainigAmount());
            //}
            System.out.println("verifyValidAccountTest Success");
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Failed");e.printStackTrace();}
    }
    
    private static void init() throws Exception{
        int[] amounts = {5,10,5,7,8,15,5,2,10,3};
        CryptoCurrencyName[] names = {CryptoCurrencyName.ETHEREUM, CryptoCurrencyName.BITCOIN, CryptoCurrencyName.BITCOIN,
        CryptoCurrencyName.RIPPLE, CryptoCurrencyName.ETHEREUM, CryptoCurrencyName.BITCOIN, CryptoCurrencyName.BITCOIN,
        CryptoCurrencyName.BITCOIN, CryptoCurrencyName.BITCOIN, CryptoCurrencyName.BITCOIN};
        int[] maxs = {10,5,29,40,45,31,30,32,35,40};
        
        ServiceBuilder builder = new DefaultServiceBuilder();
        AuthenticationService authenticationService = builder.buildAuthenticationService();
        ExchangeService exchangeService = builder.buildExchangeService();
        
        for (int i = 0; i < 10; i++){
            authenticationService.signIn(new SignInDTO("user"+i+"@gmail.com", "Password123"));
            //ApplicationCache.getInstance().currentUserBankAccount.add(new BigDecimal("2000"));
            //BankAccountSerRepository.getInstance().save(ApplicationCache.getInstance().currentUserBankAccount.getNumberAccount().getNumberAccount(), ApplicationCache.getInstance().currentUserBankAccount);
            PlaceBuyOrderDTO dto = new PlaceBuyOrderDTO(names[i], new BigDecimal(amounts[i]), new BigDecimal(maxs[i]), authenticationService.getSignedUserDTO().getId());
            exchangeService.placeBuyOrder(dto);
        }
    }
    
    private static void init_2()throws Exception{
        ServiceBuilder builder = new DefaultServiceBuilder();
        AuthenticationService authenticationService = builder.buildAuthenticationService();
        authenticationService.signIn(new SignInDTO("user7@gmail.com", "Password123"));
        ExchangeService exchangeService = builder.buildExchangeService();
        PlaceSaleOrderDTO dto = new PlaceSaleOrderDTO(CryptoCurrencyName.BITCOIN, new BigDecimal("17"), new BigDecimal("30"), authenticationService.getSignedUserDTO().getId());
        CryptoCurrency amount = (new CryptoCurrencyFactory()).createCryptoCurrency(dto.getCryptoName(), dto.getAmount());
        //ApplicationCache.getInstance().currentUserWallet.addAmount(dto.getCryptoName(), amount);
        //WalletSerRepository.getInstance().save(ApplicationCache.getInstance().currentUserWallet.getID(), ApplicationCache.getInstance().currentUserWallet);
        exchangeService.placeSaleOrder(dto);
    }
    
  public static void main(String[] args) throws Exception{
        saleOrderTest();
    }
}
