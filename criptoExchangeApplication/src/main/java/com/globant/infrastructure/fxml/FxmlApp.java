package com.globant.infrastructure.fxml;

import com.globant.application.config.Boot;
import com.globant.application.config.ServiceBuilder;
import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.application.services.wallet.WalletService;
import com.globant.domain.exceptions.DomainException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * JavaFX App
 */
public class FxmlApp extends Application implements Boot{

    private static Scene scene;
    
    public static AuthenticationService authenticationService;
    public static WalletService walletService;
    public static ExchangeService exchangeService;
    
    public FxmlApp(ServiceBuilder serviceBuilder){
        super();
        FxmlApp.authenticationService = serviceBuilder.buildAuthenticationService();
        FxmlApp.walletService = serviceBuilder.buildWalletService();
        FxmlApp.exchangeService = serviceBuilder.buildExchangeService();
    }
    
    public FxmlApp(){super();}

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("authMenu"), 640, 480);
        stage.setScene(scene);
        stage.setWidth(645);
        stage.setHeight(555);
        stage.setMinWidth(645);
        stage.setMinHeight(555);
        stage.setMaxWidth(645);
        stage.setMaxHeight(555);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void boot() {
        launch();
    }
    
    public static void showErrorMessage(DomainException e){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(e.getClass().getSuperclass().getSimpleName());
        alert.setHeaderText(e.getClass().getSimpleName());
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}