package com.mprtcz.webshop.model.purchasemodel;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.service.itemservice.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    static final Logger logger = LoggerFactory.getLogger(Cart.class);
    private Map<Item, Integer> items = new HashMap<>();
    private String cartOwnerPrincipal;
    @Autowired
    ItemService itemService;

    public void addItems(Item item, Integer quantity) {
        if (items.containsKey(item)) {
            Integer numberOfItems = items.get(item);
            numberOfItems = numberOfItems + quantity;
            items.put(item, numberOfItems);
        } else {
            items.put(item, quantity);
        }
    }

    public Map<Item, Integer> getItemsMap() {
        return items;
    }

    public BigInteger getAllItemsPrice() {
        BigInteger price = BigInteger.ZERO;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            price = price.add(entry.getKey().getPrice().multiply(BigInteger.valueOf(entry.getValue())));
        }
        return price;
    }

    public void removeItem(Integer id) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                items.remove(entry.getKey());
                break;
            }
        }
    }

    public String getCartOwnerPrincipal() {
        return cartOwnerPrincipal;
    }

    public void setCartOwnerPrincipal(String cartOwnerPrincipal) {
        this.cartOwnerPrincipal = cartOwnerPrincipal;
    }

    @PostConstruct
    public void construct() {
        logger.info("Creating cart for " +cartOwnerPrincipal);
    }

    @PreDestroy
    public void dest() {
        logger.info("Destroying cart for " +this.cartOwnerPrincipal);
        if(!items.isEmpty()){
            for (Map.Entry<Item, Integer> entry : items.entrySet()){
                itemService.extendItemStock(entry.getKey().getId(), entry.getValue());
            }
        }
    }
}
