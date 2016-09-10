package com.mprtcz.webshop.model.purchasemodel;

import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-09-10.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<Item> itemsList = new ArrayList<>();

    private String cartOwner;

    public void addItems(Item item){
        itemsList.add(item);
    }

    public List<Item> getItemsList(){
        return itemsList;
    }

    public BigInteger getAllItemsPrice(){
        BigInteger price = BigInteger.ZERO;
        for(Item i : itemsList){
            price.add(i.getPrice());
        }

        return price;
    }

    public String getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(String cartOwner) {
        this.cartOwner = cartOwner;
    }
}
