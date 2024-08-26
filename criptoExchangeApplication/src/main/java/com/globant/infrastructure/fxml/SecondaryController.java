package com.globant.infrastructure.fxml;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        FxmlApp.setRoot("primary");
    }
}