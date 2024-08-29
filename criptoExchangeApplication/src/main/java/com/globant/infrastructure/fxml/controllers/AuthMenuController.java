package com.globant.infrastructure.fxml.controllers;

import com.globant.infrastructure.fxml.FxmlApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author erillope
 */
public class AuthMenuController {
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        FxmlApp.setRoot("signUp");
    }
    
}
