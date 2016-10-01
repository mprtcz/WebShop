package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.dao.itemdao.ItemDao;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    public ItemServiceImpl(ItemDao itemDao, ImageService imageService) {
        this.itemDao = itemDao;
        this.imageService = imageService;
    }

    public ItemServiceImpl() {
    }

    @Override
    public Item findById(int id) {
        return itemDao.findById(id);
    }


    @Override
    public void saveItem(Item item) {
        itemDao.save(item);
    }

    @Override
    public void updateItem(Item item) {
        Item entity = itemDao.findById(item.getId());
        if (entity != null) {
            entity.setId(item.getId());
            entity.setItemName(item.getItemName());
            entity.setPrice(item.getPrice());
            entity.setDescription(item.getDescription());
            entity.setStock(item.getStock());
        }
    }

    @Override
    public void deleteItemById(Integer id) {
        itemDao.deleteById(id);
    }

    @Override
    public List<Item> findAllItems() {
        return itemDao.findAllInstances();
    }

    @Override
    public List<Item> getRandomItems(int amount) {
        List<Item> allItemsList = findAllItems();
        List<Item> pickedItems = new ArrayList<>();
        Collections.shuffle(allItemsList);

        for (int i = 0; i < amount; i++) {
            if(allItemsList.get(i)!=null) {
                Item item = allItemsList.get(i);
                pickedItems.add(item);
            }
            else {
                pickedItems.add(new Item());
            }
        }
        return pickedItems;
    }

    @Override
    public List<Item> searchItemsByName(String expression) {
        List<Item> items = findAllItems();
        return items.stream()
                .filter(item -> item.getItemName().toLowerCase().contains(expression))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItemsByNameAndDescription(String expression) {
        List<Item> items = findAllItems();
        return items.stream()
                .filter(item -> item.getItemName().toLowerCase().contains(expression) ||
                        item.getDescription().toLowerCase().contains(expression))
                .collect(Collectors.toList());
    }
}
