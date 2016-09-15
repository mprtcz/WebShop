package com.mprtcz.webshop.model.purchasemodel;

import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Item, Integer> itemsList = new HashMap<>();

    private String cartOwner;

    public void addItems(Item item, Integer quantity){
        if(itemsList.containsKey(item)){
            Integer currentQuantity = itemsList.get(item);
            currentQuantity = currentQuantity + quantity;
            itemsList.put(item, currentQuantity);
        } else {
            itemsList.put(item, quantity);
        }
    }

    public Map<Item, Integer> getItemsList(){
        return itemsList;
    }

    public BigInteger getAllItemsPrice(){
        BigInteger price = BigInteger.ZERO;

        for(Map.Entry<Item, Integer> entry : itemsList.entrySet()){
            price = price.add(entry.getKey().getPrice().multiply(BigInteger.valueOf(entry.getValue())));
        }

        return price;
    }

    public void removeItem(Integer id){
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()){
            if(entry.getKey().getId().equals(id)){
                itemsList.remove(entry.getKey());
                break;
            }
        }
    }

    public String getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(String cartOwner) {
        this.cartOwner = cartOwner;
    }
}
