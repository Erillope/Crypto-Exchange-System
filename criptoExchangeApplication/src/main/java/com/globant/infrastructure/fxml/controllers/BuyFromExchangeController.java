
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
public class BuyFromExchangeController implements Initializable{

    @FXML
    private TextField amountField;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    
    private CryptoCurrencyName cryptoName;
    @FXML
    private Label bitcoinPrice;
    @FXML
    private Label ethereumPrice;
    @FXML
    private Label ripplePrice;
    @FXML
    private Label bitcoinAmount;
    @FXML
    private Label ethereumAmount;
    @FXML
    private Label rippleAmount;
    @FXML
    private HBox bitcoinBox;
    @FXML
    private HBox ethereumBox;
    @FXML
    private HBox rippleBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AvailableCoinsDTO availableCoinsDTO = null;
        try {availableCoinsDTO = FxmlApp.exchangeService.getAvailableCoins();}
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
        bitcoinPrice.setText(availableCoinsDTO.getPrices().get(CryptoCurrencyName.BITCOIN).toString());
        ethereumPrice.setText(availableCoinsDTO.getPrices().get(CryptoCurrencyName.ETHEREUM).toString());
        ripplePrice.setText(availableCoinsDTO.getPrices().get(CryptoCurrencyName.RIPPLE).toString());
        bitcoinAmount.setText(availableCoinsDTO.getCryptoCurrency().get(CryptoCurrencyName.BITCOIN).toString());
        ethereumAmount.setText(availableCoinsDTO.getCryptoCurrency().get(CryptoCurrencyName.ETHEREUM).toString());
        rippleAmount.setText(availableCoinsDTO.getCryptoCurrency().get(CryptoCurrencyName.RIPPLE).toString());
        message.setText("Here you can buy cryptocurrencies\n"
                + "from the exchange,\n"
                + "the price fluctuates\n"
                + "according to sales");
    }
    
    
    @FXML
    private void exchange(ActionEvent event) {
        try{
            ExchangeCryptoCurrencyDTO dto = new ExchangeCryptoCurrencyDTO(this.cryptoName, new BigDecimal(amountField.getText().trim()),
            FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.exchangeService.exchange(dto);
                FxmlApp.setRoot("buyFromExchange");
            }
            catch(DomainException e){FxmlApp.showErrorMessage(e);} 
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
