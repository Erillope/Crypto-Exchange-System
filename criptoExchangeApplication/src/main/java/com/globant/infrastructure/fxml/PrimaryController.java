package com.globant.infrastructure.fxml;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        FxmlApp.setRoot("secondary");
    }
}
