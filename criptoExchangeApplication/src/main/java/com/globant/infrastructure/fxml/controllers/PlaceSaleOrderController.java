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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private VBox coinsVBox;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Here you can request \na sales order,\n"
                + "if you are lucky it will be \npurchased instantly,\n"
                + "if not it will be archived.\n"
                + "You decide the unit price.");
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
