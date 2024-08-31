module com.globant.criptoexchangeapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.prefs;

    opens com.globant.infrastructure.fxml to javafx.fxml;
    exports com.globant.infrastructure.fxml;
}
