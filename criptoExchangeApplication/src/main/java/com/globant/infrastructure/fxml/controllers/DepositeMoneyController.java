package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.DepositeMoneyDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InvalidAmountException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.math.BigDecimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author erillope
 */
public class DepositeMoneyController{

    @FXML
    private TextField amountField;
    
    @FXML
    private void depositeMoney(ActionEvent event) {
        try{
            DepositeMoneyDTO dto = new DepositeMoneyDTO(new BigDecimal(amountField.getText().trim()), 
                    FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.walletService.depositeMoney(dto);
                FxmlApp.setRoot("mainMenu");
            }
            catch(DomainException e){FxmlApp.showErrorMessage(e);} 
        }
        catch(Exception e){FxmlApp.showErrorMessage(InvalidAmountException.invalidAmount());}
    }

    @FXML
    private void ReturnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
}
