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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author erillope
 */
public class WalletBalanceController implements Initializable{

    @FXML
    private VBox balanceVBox;
    @FXML
    private Label usdAmountLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetWalletBalanceDTO dto = new GetWalletBalanceDTO(FxmlApp.authenticationService.getSignedUserDTO().getId());
        try{setBalance(dto);}
        catch(DomainException e){showError(e);} 
    }
    
    private void setBalance(GetWalletBalanceDTO dto) throws DomainException{
        BalanceDTO balance = FxmlApp.walletService.getBalance(dto);
        usdAmountLabel.setText(usdAmountLabel.getText()+balance.getMoney());
        Set<Map.Entry<CryptoCurrencyName,BigDecimal>> entrySet = balance.getCryptoCurrencyBalance().entrySet();
        for (Map.Entry<CryptoCurrencyName,BigDecimal> entry: entrySet){
            balanceVBox.getChildren().add(new Label(entry.getKey()+": "+entry.getValue()));
        }
    }
    
    private void showError(DomainException e){
        FxmlApp.showErrorMessage(e);
        try {
            FxmlApp.setRoot("mainMenu");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
}
