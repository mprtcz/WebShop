package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.itemmodel.ItemRecordId;
import com.mprtcz.webshop.model.itemmodel.ItemRecordOld;
import com.mprtcz.webshop.model.itemmodel.Record;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.itemservice.RecordService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    RecordService recordService;

    @Override
    public String purchase(User user, Item item, Integer amount) {

        BigInteger amountBI = BigInteger.valueOf(amount);

        if (amountBI.compareTo(item.getStock()) < 1) {
            BigInteger totalPrice = amountBI.multiply(item.getPrice());
            if (user.getBalance().compareTo(totalPrice) > -1) {
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

    public String purchaseAllCartItems(User user, Map<Item, Integer> itemsToBuyMap){
        if (cartService.getItemsValue().compareTo(user.getBalance()) > 1) {
            System.out.println("PurchaseServiceImpl.purchaseAllCartItems");
            System.out.println("not enough money");
            return "not.enough.money";
        }
        if(itemsToBuyMap.isEmpty()){
            System.out.println("PurchaseServiceImpl.purchaseAllCartItems");
            System.out.println("Cart empty");
            return "cart.empty";
        }

        for (Map.Entry entry :
                itemsToBuyMap.entrySet()) {

            List<Record> boughtItemsHistory = user.getBoughtItemsList();
            System.out.println("boughtItemsHistory = " + boughtItemsHistory);

            if(!(entry.getKey() instanceof Item)){
               throw new ClassCastException();
            }

            if(!(entry.getValue() instanceof Integer)){
                throw new ClassCastException();
            }

            Item item = (Item) entry.getKey();
            System.out.println("ITEM TO BUY:" + item);


            BigInteger transactionValue = item.getPrice().multiply(BigInteger.valueOf((Integer) entry.getValue()));

            BigInteger newBalance = (user.getBalance().subtract(transactionValue));

            BigInteger newStock = item.getStock().subtract(BigInteger.ONE);

            if (newStock.compareTo(BigInteger.ZERO) > -1 && newBalance.compareTo(BigInteger.ZERO) > -1) {

                user.setBalance(newBalance);

                item.setStock(newStock);

                Record purchaseRecord = Record.getInstance(user, item, (Integer) entry.getValue());

                boughtItemsHistory.add(purchaseRecord);

                user.setBoughtItemsList(boughtItemsHistory);
                //userService.updateUserHistory(user, purchaseRecord);
                recordService.saveRecord(purchaseRecord);
                itemService.updateItem(item);
                cartService.removeItem(item.getId());
            }
            userService.updateUser(user);
        }
        return "items.bought.success";
    }

    private String purchaseItemsList(User user, List<Item> itemsList) {
        if (cartService.getItemsValue().compareTo(user.getBalance()) > 1) {
            return "not.enough.money";
        }

        if(itemsList.isEmpty()){
            return "cart.empty";
        }

        List<Record> boughtItemsHistory = user.getBoughtItemsList();
        System.out.println("boughtItemsHistory = " + boughtItemsHistory);

        System.out.println("itemsList = " + itemsList);

        for (Item item : itemsList) {
            System.out.println("ITEM TO BUY:" + item);
            BigInteger newBalance = (user.getBalance().subtract(item.getPrice()));

            BigInteger newStock = item.getStock().subtract(BigInteger.ONE);
            if (newStock.compareTo(BigInteger.ZERO) > -1 && newBalance.compareTo(BigInteger.ZERO) > -1) {

                user.setBalance(newBalance);

                item.setStock(newStock);

                ItemRecordOld itemRecord = new ItemRecordOld();
                itemRecord.setPk(new ItemRecordId(item, user));
                itemRecord.setValue(item.getPrice());
                itemRecord.setTransactionTime(new Date(System.currentTimeMillis()));
                System.out.println("TRANSACTION TIME: " + itemRecord.getTransactionTime());

                //boughtItemsHistory.add(itemRecord);

                //userService.updateUserHistory(user, itemRecord);
                itemService.updateItem(item);
                cartService.removeItem(item.getId());
            }
            userService.updateUser(user);

        }
        return "items.bought.success";
    }

    public String purchaseAll(User user, Map<Item, Integer> itemsList) {
        List<Item> completeItemsList = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                completeItemsList.add(entry.getKey());
            }
        }
        return purchaseItemsList(user, completeItemsList);
    }


}
