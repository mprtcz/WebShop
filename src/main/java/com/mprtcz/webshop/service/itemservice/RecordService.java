package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.Record;
import com.mprtcz.webshop.model.usermodel.User;

import java.util.List;

/**
 * Created by Azet on 2016-09-18.
 */
public interface RecordService {
    Record findById(int id);

    void saveRecord(Record item);

    List<Record> findAllRecords();

    List<Record> findRecordsByUser(User user);

}
