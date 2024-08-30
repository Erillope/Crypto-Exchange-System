package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.SignInDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author erillope
 */
public class SignInController{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private void signIp(ActionEvent event) throws IOException {
        SignInDTO dto = new SignInDTO(emailField.getText(), passwordField.getText());
        try{
            FxmlApp.authenticationService.signIn(dto);
            FxmlApp.setRoot("mainMenu");
        }
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
    }

    @FXML
    private void returnMenu(ActionEvent event) throws IOException {
        FxmlApp.setRoot("authMenu");
    }
    
}
