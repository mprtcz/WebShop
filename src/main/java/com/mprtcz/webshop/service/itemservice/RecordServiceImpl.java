package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.dao.itemdao.RecordDao;
import com.mprtcz.webshop.model.itemmodel.Record;
import com.mprtcz.webshop.model.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Azet on 2016-09-18.
 */
@Service("recordService")
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public Record findById(int id) {
        return recordDao.findById(id);
    }

    @Override
    public void saveRecord(Record item) {
        recordDao.save(item);
    }

    @Override
    public List<Record> findAllRecords() {
        return recordDao.findAllInstances();
    }

    @Override
    public List<Record> findRecordsByUser(User user) {
        return recordDao.findInstancesByBuyerId(user.getId());
//        List<Record> records = recordDao.findAllInstances();
//        return records.stream()
//                .filter(record -> Objects.equals(user.getId(), record.getBuyerId()))
//                .collect(Collectors.toList());
    }
}
