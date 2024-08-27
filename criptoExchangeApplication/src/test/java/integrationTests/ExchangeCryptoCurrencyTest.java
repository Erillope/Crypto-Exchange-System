package integrationTests;

import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.application.repositories.BankAccountSerRepository;
import com.globant.application.repositories.ExchangeInstance;
import com.globant.application.repositories.Repository;
import com.globant.application.repositories.SerExchangeInstance;
import com.globant.application.repositories.UserRepository;
import com.globant.application.repositories.UserSerRepository;
import com.globant.application.repositories.WalletSerRepository;
import com.globant.application.services.exchange.ExchangeCryptoCurrencyUseCaseImpl;
import com.globant.application.services.wallet.BankTransactionExecuter;
import com.globant.application.services.wallet.BankTransactionExecuterImpl;
import com.globant.domain.crypto.Bitcoin;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.Wallet;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.factories.BankAccountFactory;
import com.globant.domain.factories.BankName;
import com.globant.domain.factories.UserFactory;
import com.globant.domain.factories.WalletFactory;
import com.globant.domain.user.BankAccount;
import com.globant.domain.user.GmailAccount;
import com.globant.domain.user.User;
import com.globant.domain.user.UserAccount;
import java.math.BigDecimal;
import com.globant.application.services.exchange.ExchangeCryptoCurrencyUseCase;

/**
 *
 * @author erillope
 */
public class ExchangeCryptoCurrencyTest {
    private static void successExchangeTest() throws Exception{
        try{
            UserRepository userRepository = UserSerRepository.getInstance();
            Repository<String, BankAccount> bankAccountRepository = BankAccountSerRepository.getInstance();
            ExchangeInstance exchangeInstance = SerExchangeInstance.getInstance();
            BankTransactionExecuter transactionExecuter = new BankTransactionExecuterImpl();
            Repository<WalletID, Wallet> walletRepository = WalletSerRepository.getInstance();
            WalletFactory walletFactory = new WalletFactory();
            
            Wallet exchangeWallet = walletFactory.createWallet();
            Wallet userWallet = walletFactory.createWallet();
            exchangeWallet.put(CryptoCurrencyName.BITCOIN, new Bitcoin(new BigDecimal("10")));
            exchangeWallet.setId(exchangeInstance.get().getWalletID());
            walletRepository.save(exchangeWallet.getID(), exchangeWallet);
            walletRepository.save(userWallet.getID(), userWallet);
            
            UserFactory userFactory = new UserFactory();
            UserAccount account = new GmailAccount("Erick", "erillope@gmail.com", "Erillope123");
            User user = userFactory.createUser("457698756", userWallet.getID(), account);
            BankAccountFactory bankAccountFactory = new BankAccountFactory();
            BankAccount exchangeBank = bankAccountFactory.createAccount(BankName.PACIFICO,"123456789");
            BankAccount userBank = bankAccountFactory.createAccount(BankName.PACIFICO,user.getNumberAccount().getNumberAccount());
            BigDecimal exchangeMoney = exchangeBank.getMoney();
            BigDecimal userMoney = userBank.getMoney();
            
            userRepository.save(user.getUserID(), user);
            bankAccountRepository.save(userBank.getNumberAccount().getNumberAccount(), userBank);
            bankAccountRepository.save(exchangeBank.getNumberAccount().getNumberAccount(), exchangeBank);
            
            ExchangeCryptoCurrencyUseCase exchangeCryptoCurrencyUseCase = new ExchangeCryptoCurrencyUseCaseImpl(userRepository, bankAccountRepository, 
                    exchangeInstance, transactionExecuter, walletRepository);
            ExchangeCryptoCurrencyDTO dto = new ExchangeCryptoCurrencyDTO(CryptoCurrencyName.BITCOIN ,new Bitcoin(new BigDecimal("3")));
            dto.setUserID(user.getUserID());
            exchangeCryptoCurrencyUseCase.exchange(dto);
            
            userMoney = userMoney.subtract(new BigDecimal("30"));
            exchangeMoney = exchangeMoney.add(new BigDecimal("30"));
            
            userBank = bankAccountRepository.get(user.getNumberAccount().getNumberAccount());
            exchangeBank = bankAccountRepository.get("123456789");
            
            boolean isValid = userMoney.equals(userBank.getMoney()) && exchangeMoney.equals(exchangeBank.getMoney());
            if (isValid){System.out.println("verifyValidAccountTest Success");}
            else{throw new Exception();}
            
        }
        catch(Exception e){System.out.println("verifyValidAccountTest Success");}
    }
    
    public static void main(String[] args) throws Exception{
        successExchangeTest();
    }
}
