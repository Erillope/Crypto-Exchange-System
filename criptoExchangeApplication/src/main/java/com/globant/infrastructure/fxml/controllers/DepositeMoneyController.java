package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.DepositeMoneyDTO;
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

/**
 *
 * @author erillope
 */
public class DepositeMoneyController implements Initializable{

    @FXML
    private TextField amountField;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Here you can add USD\n"
                + "to make purchases");
    }
    
    @FXML
    private void depositeMoney(ActionEvent event) {
        try{
            DepositeMoneyDTO dto = new DepositeMoneyDTO(new BigDecimal(amountField.getText().trim()), 
                    FxmlApp.authenticationService.getSignedUserDTO().getId());
            try{
                FxmlApp.walletService.depositeMoney(dto);
                FxmlApp.setRoot("mainMenu");
            }
            catch(DomainException e){showException(e);} 
        }
        catch(Exception e){showException(InvalidAmountException.invalidAmount());}
    }

    @FXML
    private void returnMenu(MouseEvent event) throws IOException {
        FxmlApp.setRoot("mainMenu");
    }
    
    private void showException(DomainException e){
        message.setText(e.getMessage());
        zundamonView.setImage(new Image(FxmlApp.class.getResource("/gallery/zundamon2.png").toString()));
    }
}
