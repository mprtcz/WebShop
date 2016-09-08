package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

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

    @Override
    public String purchase(User user, Item item, Integer amount) {
        System.out.println("PurchaseServiceImpl.purchase");
        BigInteger amountBI = BigInteger.valueOf(amount);

        if (amountBI.compareTo(item.getStock()) < 1) {

            BigInteger totalPrice = amountBI.multiply(item.getPrice());
            System.out.println("totalPrice = " + totalPrice);
            System.out.println("user.getBalance() = " + user.getBalance());
            System.out.println("user.getBalance().compareTo(totalPrice)" +user.getBalance().compareTo(totalPrice));
            if(user.getBalance().compareTo(totalPrice) > -1) {

                BigInteger newBalance = (user.getBalance().subtract(item.getPrice()));
                user.setBalance(newBalance);
                BigInteger initialStock = item.getStock();
                List<Item> itemsList = user.getBoughtItemsList();
                item.setStock(amountBI);
                itemsList.add(item);
                user.setBoughtItemsList(itemsList);
                BigInteger newStock = initialStock.subtract(amountBI);
                item.setStock(newStock);

                userService.updateUser(user);
                itemService.updateItem(item);
            } else {
                System.out.println("not enough money");
                return "not enough money";
            }
        } else {
            System.out.println("not enough items in stock");
            return "not enough items in stock";
        }
        System.out.println("SUCCESS");
        return "success";
    }
}
