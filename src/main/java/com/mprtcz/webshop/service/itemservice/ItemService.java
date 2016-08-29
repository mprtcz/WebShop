package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.Item;

import java.util.List;

/**
 * Created by Azet on 2016-08-29.
 */
public interface ItemService {

    Item findById(int id);

    Item findBySellerId(Integer sellerId);

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItemById(Integer id);

    List<Item> findAllItems();
}
