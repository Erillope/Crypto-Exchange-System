package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.SignUpDTO;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.factories.BankName;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author erillope
 */
public class SignUpController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private ChoiceBox<BankName> banckAccountChoice;
    @FXML
    private TextField numberAccountField;
    
    private AuthenticationService authenticationService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.authenticationService = FxmlApp.serviceBuilder.buildAuthenticationService();
        banckAccountChoice.getItems().addAll(BankName.values());
    }    
    
    @FXML
    private void signUp(ActionEvent event) throws IOException{
        SignUpDTO dto= new SignUpDTO(nameField.getText(), emailField.getText(), passwordField.getText(),
                numberAccountField.getText(),banckAccountChoice.getValue());
        try{
            this.authenticationService.signUp(dto);
            FxmlApp.setRoot("mainMenu");
        }
        catch(DomainException e){FxmlApp.showErrorMessage(e);}  
    }

}
