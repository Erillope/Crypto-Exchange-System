package com.globant.infrastructure.fxml;

import com.globant.application.config.Boot;
import com.globant.application.config.ServiceBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class FxmlApp extends Application implements Boot{

    private static Scene scene;
    
    private final ServiceBuilder serviceBuilder;
    
    public FxmlApp(ServiceBuilder serviceBuilder){
        this.serviceBuilder = serviceBuilder;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @Override
    public void boot() {
        launch();
    }
}