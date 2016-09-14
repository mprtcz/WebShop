package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.usermodel.User;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
public interface CartService {
    void addItemsToCart(Item item, Integer quantity, User currentUser);

    Map<Item, Integer> getItemsInCart(User user);

    BigInteger getItemsValue();

    void removeItem(User user, Integer itemId);
}
