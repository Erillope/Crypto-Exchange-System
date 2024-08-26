package com.globant.application.config;

import application.services.authentication.AuthenticationService;
import application.services.exchange.ExchangeService;
import application.services.wallet.WalletService;

/**
 *
 * @author erillope
 */
public interface ServiceBuilder {
    public AuthenticationService buildAuthenticationService();
    
    public WalletService buildWalletService();
    
    public ExchangeService buildExchangeService();
}
