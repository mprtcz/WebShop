package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.usermodel.User;

/**
 * Created by Azet on 2016-09-08.
 */
public interface PurchaseService {

    String purchase(User user, Item item, Integer amount);
}
