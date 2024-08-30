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
        FxmlApp.setRoot("viewWalletBalance");
    }

}
