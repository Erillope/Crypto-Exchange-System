package com.globant.application;

import com.globant.application.config.Boot;
import com.globant.application.config.DefaultServiceBuilder;
import com.globant.application.config.ServiceBuilder;
import com.globant.infrastructure.fxml.FxmlApp;

/**
 *
 * @author erillope
 */
public class CriptoExchangeApplication {
    public static void start(){
        ServiceBuilder serviceBuilder = new DefaultServiceBuilder();
        Boot boot = new FxmlApp(serviceBuilder);
        boot.boot();
    }
    
    public static void main(String[] args) {
        start();
    }
}
