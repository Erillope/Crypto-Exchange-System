package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.SignInDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
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
public class SignInController implements Initializable{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Hello, my name is Zundamon.\n"
                + "After you log in \n"
                + "I will guide you through\n"
                + "this app");
    }
    
    @FXML
    private void signUp(MouseEvent event) throws IOException {
        FxmlApp.setRoot("authMenu");
    }

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        SignInDTO dto = new SignInDTO(emailField.getText().trim().toLowerCase(), passwordField.getText().trim());
        try{
            FxmlApp.authenticationService.signIn(dto);
            FxmlApp.setRoot("mainMenu");
        }
        catch(DomainException e){showException(e);}
    }
    
    private void showException(DomainException e){
        message.setText(e.getMessage());
        zundamonView.setImage(new Image(FxmlApp.class.getResource("/gallery/zundamon2.png").toString()));
    }
}
