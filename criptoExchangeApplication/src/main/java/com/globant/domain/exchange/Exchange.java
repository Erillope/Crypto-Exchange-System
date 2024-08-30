package com.globant.domain.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.globant.domain.crypto.CryptoCurrencyName;
import com.globant.domain.crypto.WalletID;
import com.globant.domain.crypto.WalletUUID;
import com.globant.domain.exceptions.InvalidNumberAccountException;
import com.globant.domain.user.NumberAccount;
import com.globant.domain.util.OnlyReadCollection;
import com.globant.domain.util.OnlyReadCollectionImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author erillope
 */
public class Exchange {
    private static Exchange instance = null;
    private final static Map<CryptoCurrencyName, BigDecimal> initialPrices;
    static {
        initialPrices = new HashMap<>();
        initialPrices.put(CryptoCurrencyName.BITCOIN, new BigDecimal("10"));
        initialPrices.put(CryptoCurrencyName.ETHEREUM, new BigDecimal("5"));
        initialPrices.put(CryptoCurrencyName.RIPPLE, new BigDecimal("3"));
    }
    
    private NumberAccount numberAccount;
    private WalletID walletID;
    private Map<CryptoCurrencyName, BigDecimal> marketPrices;
    private final List<BuyOrder> buyOrderBook;
    private final List<SalesOrder> salesOrderBook;
    
    private Exchange(){
        try{numberAccount = new NumberAccount("6578435789");}
        catch(InvalidNumberAccountException e){e.printStackTrace();}
        walletID = new WalletUUID();
        marketPrices = new HashMap<>();
        buyOrderBook = new ArrayList<>();
        salesOrderBook = new ArrayList<>();
    }
    
    public OnlyReadCollection<SalesOrder> searchSalesOrder(BuyOrder buyOrder){
        List<SalesOrder> orders = salesOrderBook.stream().
                filter(o -> o.getMinPrice().compareTo(buyOrder.getMaxPrice()) <= 0).
                collect(Collectors.toList());
        orders.sort(Comparator.comparing(SalesOrder::getMinPrice));
        List<SalesOrder> selectedOrders = new ArrayList<>();
        BigDecimal amount = BigDecimal.ZERO;
        for (SalesOrder order: orders){
            if (amount.compareTo(buyOrder.getAmount().getAmount()) > 0){
                return new OnlyReadCollectionImpl(selectedOrders);
            }
            amount.add(buyOrder.getAmount().getAmount());
            selectedOrders.add(order);
        }
        return new OnlyReadCollectionImpl(new ArrayList<>());
    }
    
    public OnlyReadCollection<BuyOrder> searchBuyOrder(SalesOrder salesOrder){
        List<BuyOrder> orders = buyOrderBook.stream().
                filter(o -> o.getMaxPrice().compareTo(salesOrder.getMinPrice()) >= 0).
                collect(Collectors.toList());
        orders.sort(Comparator.comparing(BuyOrder::getMaxPrice).reversed());
        List<BuyOrder> selectedOrders = new ArrayList<>();
        BigDecimal amount = BigDecimal.ZERO;
        for (BuyOrder order: orders){
            if (amount.compareTo(salesOrder.getAmount().getAmount()) > 0){
                return new OnlyReadCollectionImpl(selectedOrders);
            }
            amount.add(salesOrder.getAmount().getAmount());
            selectedOrders.add(order);
        }
        return new OnlyReadCollectionImpl(new ArrayList<>());
    }
    
    public BigDecimal getPrice(CryptoCurrencyName cryptoName){return marketPrices.get(cryptoName);}
    
    public void addPrice(CryptoCurrencyName cryptoName, BigDecimal price){marketPrices.put(cryptoName, price);}
    
    public NumberAccount getNumberAccount(){return numberAccount;}
    
    public WalletID getWalletID(){return walletID;}
    
    public void setWalletID(WalletID id){walletID = id;}
    
    public static Exchange getInstance(){
        if (instance != null){return instance;}
        return new Exchange();
    }
    
    public static BigDecimal getInitialPrice(CryptoCurrencyName cryptoName){return initialPrices.get(cryptoName);}
 }