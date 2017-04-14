package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.Item;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Azet on 2016-08-29.
 */
public interface ItemService {

    Item findById(int id);

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItemById(Integer id);

    List<Item> findAllItems();

    List<Item> getRandomItems(int amount);

    List<Item> searchItemsByName(String expression);

    List<Item> searchItemsByNameAndDescription(String expression);

    void extendItemStock(Integer itemID, Integer quantity);

    void subtractItemStock(Item item, BigInteger quantityToSubtract);

    boolean isEnoughItemsInStock(Item item, BigInteger quantityToCheck);
}
