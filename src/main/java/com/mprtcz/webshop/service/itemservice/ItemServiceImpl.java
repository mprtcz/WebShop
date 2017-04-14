package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.dao.itemdao.ItemDao;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
        List<Item> pickedItems = populateListWithNewItems(amount);
        Collections.shuffle(allItemsList);

        for (int i = 0; i < pickedItems.size(); i++) {
            if(allItemsList.get(i)!=null) {
                Item item = allItemsList.get(i);
                pickedItems.set(i, item);
            }
        }
        return pickedItems;
    }

    private List<Item> populateListWithNewItems(int size) {
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Item());
        }
        return list;
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

    @Override
    public void extendItemStock(Integer itemID, Integer quantity) {
        Item item = findById(itemID);
        item.addStock(BigInteger.valueOf(quantity));
        updateItem(item);
    }

    @Override
    public void subtractItemStock(Item item, BigInteger quantityToSubtract) {
        BigInteger newStock = item.getStock().subtract(quantityToSubtract);
        item.setStock(newStock);
        updateItem(item);
    }

    @Override
    public boolean isEnoughItemsInStock(Item item, BigInteger quantityToCheck) {
        return item.getStock().compareTo(quantityToCheck) >= 0;
    }
}
