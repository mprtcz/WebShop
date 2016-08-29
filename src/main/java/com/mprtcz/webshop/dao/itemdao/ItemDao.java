package com.mprtcz.webshop.dao.itemdao;

import com.mprtcz.webshop.model.itemmodel.Item;

import java.util.List;

/**
 * Created by Azet on 2016-08-29.
 */
public interface ItemDao {

    Item findById(int id);

    Item findBySellerId(Integer sellerId);

    void save(Item item);

    void deleteById(Integer id);

    List<Item> findAllItems();

    List<Item> findItemsBySellerId(Integer sellerId);
}
