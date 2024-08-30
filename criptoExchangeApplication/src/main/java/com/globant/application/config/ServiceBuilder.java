package com.globant.application.config;

import com.globant.application.services.authentication.AuthenticationService;
import com.globant.application.services.exchange.ExchangeService;
import com.globant.application.services.wallet.WalletService;

/**
 *
 * @author erillope
 */
public interface ServiceBuilder {
    
    public Initializer buildInitializer();
    
    public AuthenticationService buildAuthenticationService();
    
    public WalletService buildWalletService();
    
    public ExchangeService buildExchangeService();
}
