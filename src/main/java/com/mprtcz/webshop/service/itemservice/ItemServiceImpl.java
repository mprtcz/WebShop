package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.dao.itemdao.ItemDao;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Azet on 2016-08-29.
 */
@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Autowired
    ImageService imageService;

    @Override
    public Item findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public List<Item> findItemsBySellerId(Integer sellerId) {
        return itemDao.findItemsBySellerId(sellerId);
    }

    @Override
    public void saveItem(Item item) {
        itemDao.save(item);
    }

    @Override
    public void updateItem(Item item) {
        Item entity = itemDao.findById(item.getId());
        if(entity!=null){
            entity.setId(item.getId());
            entity.setItemName(item.getItemName());
            entity.setPrice(item.getPrice());
            entity.setDescription(item.getDescription());
        }
    }

    @Override
    public void deleteItemById(Integer id) {
        itemDao.deleteById(id);
    }

    @Override
    public List<Item> findAllItems() {
        return itemDao.findAllItems();
    }

    @Override
    public List<Item> getRandomItemsWithPictures(int amount) {
        List<Item> allItemsList = findAllItems();
        List<Item> pickedItems = new ArrayList<>();
        Collections.shuffle(allItemsList);

        for (int i = 0; i < amount; i++) {
            Item item = allItemsList.get(i);
            item.setFile(imageService.getImagesByItemId(item.getId()));
            pickedItems.add(item);
        }
        return pickedItems;
    }
}
