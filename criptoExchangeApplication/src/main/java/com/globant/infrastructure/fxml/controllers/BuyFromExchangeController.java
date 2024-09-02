
package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.AvailableCoinsDTO;
import com.globant.application.dto.ExchangeCryptoCurrencyDTO;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class BuyFromExchangeController implements Initializable{

    @FXML
    private VBox coinsVBox;
    @FXML
    private TextField amountField;
    
    private CryptoCurrencyName cryptoName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AvailableCoinsDTO availableCoinsDTO = null;
        try {availableCoinsDTO = FxmlApp.exchangeService.getAvailableCoins();}
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
        for (CryptoCurrencyName cryptoName: CryptoCurrencyName.values()){
            addCoinButton(availableCoinsDTO, cryptoName);
        }
    }
    
   private void addCoinButton(AvailableCoinsDTO availableCoinsDTO, CryptoCurrencyName cryptoName){
       BigDecimal amount = availableCoinsDTO.getCryptoCurrency().get(cryptoName).getAmount();
       BigDecimal price = availableCoinsDTO.getPrices().get(cryptoName);
       HBox hBox = new HBox();
       Button button = new Button(cryptoName.name());
       hBox.getChildren().add(new Label("amount: "+amount+"; price: "+price));
       button.setOnAction(e -> {this.cryptoName = cryptoName;});
       hBox.getChildren().add(button);
       coinsVBox.getChildren().add(hBox);
   }

    @FXML
    private void buy(ActionEvent event) {
        try{
            ExchangeCryptoCurrencyDTO dto = new ExchangeCryptoCurrencyDTO(this.cryptoName, new BigDecimal(amountField.getText().trim()),
            FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.exchangeService.exchange(dto);
                FxmlApp.setRoot("buyFromExchange");
            }
            catch(DomainException e){FxmlApp.showErrorMessage(e);} 
        }
        catch(Exception e){FxmlApp.showErrorMessage(InvalidAmountException.invalidAmount());}
        
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
}
