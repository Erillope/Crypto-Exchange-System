package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.BalanceDTO;
import com.globant.application.dto.GetWalletBalanceDTO;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.exceptions.DomainException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class WalletBalanceController implements Initializable{

    @FXML
    private Label bitCoinAmount;
    @FXML
    private Label ethereumAmount;
    @FXML
    private Label rippleAmount;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    @FXML
    private Label moneyAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetWalletBalanceDTO dto = new GetWalletBalanceDTO(FxmlApp.authenticationService.getSignedUserDTO().getId());
        try{setBalance(dto);}
        catch(DomainException e){showError(e);} 
        message.setText("Here you can check\n"
                + "your wallet balance,\n"
                + "all your money and\n"
                + "cryptocurrencies are here");
    }
    
    private void setBalance(GetWalletBalanceDTO dto) throws DomainException{
        BalanceDTO balance = FxmlApp.walletService.getBalance(dto);
        moneyAmount.setText(balance.getMoney().toString());
        bitCoinAmount.setText(balance.getCryptoCurrencyBalance().get(CryptoCurrencyName.BITCOIN).toString());
        ethereumAmount.setText(balance.getCryptoCurrencyBalance().get(CryptoCurrencyName.ETHEREUM).toString());
        rippleAmount.setText(balance.getCryptoCurrencyBalance().get(CryptoCurrencyName.RIPPLE).toString());
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
