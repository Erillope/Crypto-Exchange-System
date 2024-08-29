package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.SignInDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.domain.exceptions.DomainException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author erillope
 */
public class SignInController implements Initializable{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
   
    private AuthenticationService authenticationService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.authenticationService = FxmlApp.serviceBuilder.buildAuthenticationService();
    } 

    @FXML
    private void signIp(ActionEvent event) throws IOException {
        SignInDTO dto = new SignInDTO(emailField.getText(), passwordField.getText());
        try{
            this.authenticationService.signIn(dto);
            FxmlApp.setRoot("authMenu");
        }
        catch(DomainException e){FxmlApp.showErrorMessage(e);}
    }
    
}
