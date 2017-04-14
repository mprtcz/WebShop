package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
public interface CartService {
    void addItemsToCart(Item item, Integer quantity);

    Map<Item, Integer> getItemsInCart();

    BigInteger getItemsValue();

    void removeItem(Integer itemId);

}
