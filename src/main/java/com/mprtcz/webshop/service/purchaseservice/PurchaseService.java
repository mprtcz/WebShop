package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.usermodel.User;

import java.util.Map;

/**
 * Created by Azet on 2016-09-08.
 */
public interface PurchaseService {

    String purchase(User user, Item item, Integer amount);

    String purchaseAll(User user, Map<Item, Integer> itemsList);

    String purchaseAllCartItems(User user, Map<Item, Integer> itemsToBuyMap);
}
