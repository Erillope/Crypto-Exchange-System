package com.globant.domain.crypto;

/**
 *
 * @author erillope
 */
public interface IncomePayment {
    public static CryptoCurrencyName CRYPTO_NAME = null;
    public void addAmount(Wallet wallet, CryptoCurrency amount);
}