module com.globant.criptoexchangeapplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.globant.criptoexchangeapplication to javafx.fxml;
    exports com.globant.criptoexchangeapplication;
}
