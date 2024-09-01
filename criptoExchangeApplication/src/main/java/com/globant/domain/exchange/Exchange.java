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
import com.globant.domain.util.OnlyReadMap;
import com.globant.domain.util.OnlyReadMapImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author erillope
 */
public class Exchange implements Serializable{
    private static Exchange instance = null;
    private NumberAccount numberAccount;
    private WalletID walletID;
    private final Map<CryptoCurrencyName, BigDecimal> marketPrices;
    public final List<BuyOrder> buyOrderBook;
    public final List<SalesOrder> salesOrderBook;
    
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
                filter(o -> o.getMinPrice().compareTo(buyOrder.getMaxPrice()) <= 0 
                        && o.getCryptoName() == buyOrder.getCryptoName() && !o.getUserID().equals(buyOrder.getUserID())).
                collect(Collectors.toList());
        orders.sort(Comparator.comparing(SalesOrder::getMinPrice));
        List<SalesOrder> selectedOrders = new ArrayList<>();
        BigDecimal amount = BigDecimal.ZERO;
        for (SalesOrder order: orders){
            if (amount.compareTo(buyOrder.getRemainigAmount()) > 0){
                return new OnlyReadCollectionImpl(selectedOrders);
            }
            amount = amount.add(order.getRemainigAmount());
            selectedOrders.add(order);
        }
        return new OnlyReadCollectionImpl(new ArrayList<>());
    }
    
    public OnlyReadCollection<BuyOrder> searchBuyOrder(SalesOrder salesOrder){
        List<BuyOrder> orders = buyOrderBook.stream().
                filter(o -> o.getMaxPrice().compareTo(salesOrder.getMinPrice()) >= 0 
                        && o.getCryptoName() == salesOrder.getCryptoName() && !o.getUserID().equals(salesOrder.getUserID())).
                collect(Collectors.toList());
        System.out.println("orders sin ordenar");
        orders.sort(Comparator.comparing(BuyOrder::getMaxPrice).reversed());
        List<BuyOrder> selectedOrders = new ArrayList<>();
        BigDecimal amount = BigDecimal.ZERO;
        for (BuyOrder order: orders){
            if (amount.compareTo(salesOrder.getRemainigAmount()) > 0){
                return new OnlyReadCollectionImpl(selectedOrders);
            }
            amount = amount.add(order.getRemainigAmount());
            selectedOrders.add(order);
        }
        return new OnlyReadCollectionImpl(new ArrayList<>());
    }
    
    public BigDecimal getPrice(CryptoCurrencyName cryptoName){return marketPrices.get(cryptoName);}
    
    public void addPrice(CryptoCurrencyName cryptoName, BigDecimal price){marketPrices.put(cryptoName, price);}
    
    public OnlyReadMap<CryptoCurrencyName, BigDecimal> getMarketPrices()
    {return new OnlyReadMapImpl<>(marketPrices);}
    
    public NumberAccount getNumberAccount(){return numberAccount;}
    
    public WalletID getWalletID(){return walletID;}
    
    public void setWalletID(WalletID id){walletID = id;}
    
    public static Exchange getInstance(){
        if (instance != null){return instance;}
        return new Exchange();
    }
}