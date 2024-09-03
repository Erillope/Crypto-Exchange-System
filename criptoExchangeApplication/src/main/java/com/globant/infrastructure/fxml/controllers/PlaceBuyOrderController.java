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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author erillope
 */
public class PlaceBuyOrderController implements Initializable{
    
    private CryptoCurrencyName cryptoName;
    @FXML
    private HBox bitcoinBox;
    @FXML
    private HBox ethereumBox;
    @FXML
    private HBox rippleBox;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    @FXML
    private TextField maxPriceField;
    @FXML
    private TextField amountField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Here you can request \na buy order,\n"
                + "if you are lucky it will be \nsold instantly,\n"
                + "if not it will be archived.\n"
                + "You decide the unit price.");
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
            catch(DomainException e){showError(e);} 
        }
        catch(Exception e){showError(InvalidAmountException.invalidAmount());}
    }


    @FXML
    private void selectBitcoin(MouseEvent event) {
        this.cryptoName = CryptoCurrencyName.BITCOIN;
        bitcoinBox.getStyleClass().add("hbox-activated");
        ethereumBox.getStyleClass().remove("hbox-activated");
        rippleBox.getStyleClass().remove("hbox-activated");
    }

    @FXML
    private void selectEthereum(MouseEvent event) {
        this.cryptoName = CryptoCurrencyName.ETHEREUM;
        bitcoinBox.getStyleClass().remove("hbox-activated");
        ethereumBox.getStyleClass().add("hbox-activated");
        rippleBox.getStyleClass().remove("hbox-activated");
    }

    @FXML
    private void selectRipple(MouseEvent event) {
        this.cryptoName = CryptoCurrencyName.RIPPLE;
        bitcoinBox.getStyleClass().remove("hbox-activated");
        ethereumBox.getStyleClass().remove("hbox-activated");
        rippleBox.getStyleClass().add("hbox-activated");
    }


    @FXML
    private void returnMenu(MouseEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
    private void showError(DomainException e){
        message.setText(e.getMessage());
        zundamonView.setImage(new Image(FxmlApp.class.getResource("/gallery/zundamon2.png").toString()));
    }
}
