package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.PlaceBuyOrderDTO;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class PlaceBuyOrderController implements Initializable{

    @FXML
    private VBox coinsVBox;
    @FXML
    private TextField amountField;
    @FXML
    private TextField maxPriceField;
    
    private CryptoCurrencyName cryptoName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (CryptoCurrencyName cryptoName: CryptoCurrencyName.values()){
            addCoinButton(cryptoName);
        }
    }

    @FXML
    private void placeOrder(ActionEvent event) {
        try{
            PlaceBuyOrderDTO dto = new PlaceBuyOrderDTO(this.cryptoName, new BigDecimal(amountField.getText().trim()),
                    new BigDecimal(maxPriceField.getText()), FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.exchangeService.placeBuyOrder(dto);
                FxmlApp.setRoot("placeBuyOrder");
            }
            catch(DomainException e){FxmlApp.showErrorMessage(e);} 
        }
        catch(Exception e){FxmlApp.showErrorMessage(InvalidAmountException.invalidAmount());}
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
    private void addCoinButton(CryptoCurrencyName cryptoName){
       HBox hBox = new HBox();
       Button button = new Button(cryptoName.name());
       button.setOnAction(e -> {this.cryptoName = cryptoName;});
       hBox.getChildren().add(button);
       coinsVBox.getChildren().add(hBox);
   }
}
