package com.globant.infrastructure.fxml.controllers;

import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author erillope
 */
public class MainMenuController{

    @FXML
    private void depositeMoney(ActionEvent event) throws IOException {
        FxmlApp.setRoot("depositeMoney");
    }

    @FXML
    private void viewWalletBalance(ActionEvent event) throws IOException {
        FxmlApp.setRoot("WalletBalance");
    }

    @FXML
    private void buyFromExchange(ActionEvent event) throws IOException {
        FxmlApp.setRoot("buyFromExchange");
    }

    @FXML
    private void viewTransactionHistory(ActionEvent event) throws IOException {
        FxmlApp.setRoot("TransactionHistory");
    }

    @FXML
    private void placeBuyOrder(ActionEvent event) throws IOException {
        FxmlApp.setRoot("placeBuyOrder");
    }

    @FXML
    private void placeSaleOrder(ActionEvent event) throws IOException {
        FxmlApp.setRoot("placeSaleOrder");
    }

    @FXML
    private void signOut(ActionEvent event) throws IOException {
        FxmlApp.authenticationService.signOut();
        FxmlApp.setRoot("authMenu");
    }

}
