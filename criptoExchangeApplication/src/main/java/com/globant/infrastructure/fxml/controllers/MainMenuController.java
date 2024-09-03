package com.globant.infrastructure.fxml.controllers;

import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author erillope
 */
public class MainMenuController implements Initializable{

    @FXML
    private ImageView zundamonView;
    @FXML
    private Label message;

    @FXML
    private void depositeMoney(MouseEvent event) throws IOException {
        FxmlApp.setRoot("depositeMoney");
    }

    @FXML
    private void walletBalance(MouseEvent event) throws IOException {
        FxmlApp.setRoot("WalletBalance");
    }

    @FXML
    private void buyFromExchange(MouseEvent event) throws IOException {
        FxmlApp.setRoot("buyFromExchange");
    }

    @FXML
    private void transactionHistory(MouseEvent event) throws IOException {
        FxmlApp.setRoot("TransactionHistory");
    }

    @FXML
    private void placeBuyOrder(MouseEvent event) throws IOException {
        FxmlApp.setRoot("placeBuyOrder");
    }

    @FXML
    private void PlaceSaleOrder(MouseEvent event) throws IOException {
        FxmlApp.setRoot("placeSaleOrder");
    }

    @FXML
    private void signOut(MouseEvent event) throws IOException {
        FxmlApp.authenticationService.signOut();
        FxmlApp.setRoot("authMenu");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message.setText("Welcome! "+FxmlApp.authenticationService.getSignedUserDTO().getName());
    }
}
