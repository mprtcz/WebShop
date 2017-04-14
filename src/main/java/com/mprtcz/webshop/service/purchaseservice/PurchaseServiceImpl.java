package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.itemmodel.Record;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.itemservice.RecordService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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

    public String purchaseAllCartItems(User user, Map<Item, Integer> itemsToBuyMap) {
        if (cartService.getItemsValue().compareTo(user.getBalance()) > 1) {
            return "not.enough.money";
        }
        if (itemsToBuyMap.isEmpty()) {
            return "cart.empty";
        }
        for (Map.Entry<Item, Integer> entry : itemsToBuyMap.entrySet()) {
            List<Record> boughtItemsHistory = user.getBoughtItemsList();
            Item item = entry.getKey();
            BigInteger transactionValue = item.getPrice().multiply(BigInteger.valueOf(entry.getValue()));
            BigInteger newBalance = user.getBalance().subtract(transactionValue);
            BigInteger newStock = item.getStock().subtract(BigInteger.ONE);
            if(areValuesEqualToZeroOrGreater(newStock, newBalance)) {
                user.setBalance(newBalance);
                item.setStock(newStock);
                Record purchaseRecord = Record.getInstance(user, item, entry.getValue());
                boughtItemsHistory.add(purchaseRecord);
                user.setBoughtItemsList(boughtItemsHistory);
                recordService.saveRecord(purchaseRecord);
                itemService.updateItem(item);
                cartService.removeItem(item.getId());
            }
            userService.updateUser(user);
        }
        return "items.bought.success";
    }

    private boolean areValuesEqualToZeroOrGreater(BigInteger... values) {
        for (BigInteger bigInteger :
                values) {
            if(bigInteger.signum() == -1) {
                return false;
            }
        }
        return true;
    }
}
