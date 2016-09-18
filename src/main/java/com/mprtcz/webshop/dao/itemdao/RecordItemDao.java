package com.mprtcz.webshop.dao.itemdao;

import java.util.List;

/**
 * Created by Azet on 2016-09-18.
 */
public interface RecordItemDao<T> {

    T findById(int id);

    List<T> findInstancesByNameFragment(String nameFragment);

    void save(T t);


    List<T> findAllInstances();


}
