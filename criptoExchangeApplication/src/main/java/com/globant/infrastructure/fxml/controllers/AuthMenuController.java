package com.globant.infrastructure.fxml.controllers;

import com.globant.application.dto.SignUpDTO;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.accounts.BankName;
import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author erillope
 */
public class AuthMenuController implements Initializable{

    @FXML
    private TextField userNameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView pacificoView;
    @FXML
    private ImageView pichinchaView;
    @FXML
    private TextField bankNumberField;
    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;
    
    private BankName bankAccountChoice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Hello, my name is Zundamon.\n"
                + "After you log in \n"
                + "I will guide you through\n"
                + "this app");
    }
    
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        SignUpDTO dto = new SignUpDTO(userNameField.getText().trim(), emailField.getText().trim().toLowerCase(), passwordField.getText(),
                bankNumberField.getText().trim(),bankAccountChoice);
        try{
            FxmlApp.authenticationService.signUp(dto);
            message.setText(FxmlApp.authenticationService.getSignedUserDTO().getName());
            FxmlApp.setRoot("mainMenu");
        }
        catch(DomainException e){showException(e);} 
    }

    @FXML
    private void signIn(MouseEvent event) throws IOException {
        FxmlApp.setRoot("signIn");
    }

    @FXML
    private void clickPacificoView(MouseEvent event) {
        this.pacificoView.getStyleClass().remove("image-view-container");
        this.pacificoView.getStyleClass().add("image-view-container-activated");
        this.pichinchaView.getStyleClass().remove("image-view-container-activated");
        this.pichinchaView.getStyleClass().add("image-view-container");
        this.bankAccountChoice = BankName.PACIFICO;
    }

    @FXML
    private void clickPichinchaView(MouseEvent event) {
        this.pichinchaView.getStyleClass().remove("image-view-container");
        this.pichinchaView.getStyleClass().add("image-view-container-activated");
        this.pacificoView.getStyleClass().remove("image-view-container-activated");
        this.pacificoView.getStyleClass().add("image-view-container");
        this.bankAccountChoice = BankName.PICHINCHA;
    }
    
    private void showException(DomainException e){
        message.setText(e.getMessage());
        zundamonView.setImage(new Image(FxmlApp.class.getResource("/gallery/zundamon2.png").toString()));
    }
}
