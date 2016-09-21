package com.mprtcz.webshop.dao.itemdao;

import com.mprtcz.webshop.model.itemmodel.Item;

/**
 * Created by Azet on 2016-08-29.
 */
public interface ItemDao extends RecordItemDao<Item> {
    void deleteById(Integer id);
}
