package com.mprtcz.webshop.dao.itemdao;

import com.mprtcz.webshop.model.itemmodel.Record;

import java.util.List;

/**
 * Created by Azet on 2016-09-18.
 */
public interface RecordDao extends RecordItemDao<Record>{
    List<Record> findInstancesByBuyerId(Integer sellerId);
}
