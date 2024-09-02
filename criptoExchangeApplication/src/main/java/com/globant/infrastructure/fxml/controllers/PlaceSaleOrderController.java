package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.PlaceSaleOrderDTO;
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
public class PlaceSaleOrderController implements Initializable{

    @FXML
    private TextField amountField;
    @FXML
    private TextField minPriceField;
    @FXML
    private VBox coinsVBox;
    
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
            PlaceSaleOrderDTO dto = new PlaceSaleOrderDTO(this.cryptoName, new BigDecimal(amountField.getText().trim()),
                    new BigDecimal(minPriceField.getText()), FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.exchangeService.placeSaleOrder(dto);
                FxmlApp.setRoot("placeSaleOrder");
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
