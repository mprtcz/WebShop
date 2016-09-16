package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.itemmodel.ItemRecord;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Azet on 2016-09-08.
 */
@Service("purchaseService")
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Override
    public String purchase(User user, Item item, Integer amount) {
        
        BigInteger amountBI = BigInteger.valueOf(amount);

        if (amountBI.compareTo(item.getStock()) < 1) {

            BigInteger totalPrice = amountBI.multiply(item.getPrice());
            
            
            
            if(user.getBalance().compareTo(totalPrice) > -1) {

                BigInteger newBalance = (user.getBalance().subtract(item.getPrice()));
                
                user.setBalance(newBalance);

                BigInteger initialStock = item.getStock();
                

                //List<Item> itemsList = user.getBoughtItemsList();
                
                item.setStock(amountBI);
                
                //itemsList.add(item);

                //user.setBoughtItemsList(itemsList);
                BigInteger newStock = initialStock.subtract(amountBI);
                
                item.setStock(newStock);
                

                userService.updateUser(user);
                itemService.updateItem(item);
            } else {
                
                return "not enough money";
            }
        } else {
            
            return "not enough items in stock";
        }
        
        return "success";
    }

    private String purchaseItemsList(User user, List<Item> itemsList){
        if(cartService.getItemsValue().compareTo(user.getBalance()) > 1){
            return "not.enough.money";
        }

        List<ItemRecord> boughtItemsHistory = user.getBoughtItemsList();
        for (ItemRecord itemRecord :
                boughtItemsHistory) {
            System.out.println("BOUGHT ITEM: " +itemRecord.getItem());
        }

        for (Item item : itemsList) {
            System.out.println("ITEM TO BUY:" +item);
            BigInteger newBalance = (user.getBalance().subtract(item.getPrice()));

            BigInteger newStock = item.getStock().subtract(BigInteger.ONE);
            if (newStock.compareTo(BigInteger.ZERO) > -1 && newBalance.compareTo(BigInteger.ZERO) > -1) {

                user.setBalance(newBalance);

                item.setStock(newStock);

                ItemRecord itemRecord = new ItemRecord();
/*
                itemRecord.setPk(new ItemRecordId(item, user));
                itemRecord.setValue(item.getPrice());
                itemRecord.setTransactionTime(new Date());
                System.out.println(itemRecord.getTransactionTime());

                boughtItemsHistory.add(itemRecord);
                user.setBoughtItemsList(boughtItemsHistory);
//*/
                itemService.updateItem(item);
                cartService.removeItem(user, item.getId());
            }

            userService.updateUser(user);

        }
        return "items.bought.success";
    }

    public String purchaseAll(User user, Map<Item, Integer> itemsList){
        List<Item> completeItemsList = new ArrayList<>();
        for(Map.Entry<Item,Integer> entry : itemsList.entrySet()){
            for (int i = 0; i < entry.getValue(); i++) {
                completeItemsList.add(entry.getKey());
            }
        }
        return purchaseItemsList(user, completeItemsList);
    }
}
