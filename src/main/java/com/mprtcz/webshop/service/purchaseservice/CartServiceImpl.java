package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.purchasemodel.Cart;
import com.mprtcz.webshop.model.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Azet on 2016-09-10.
 */
@Service("cartService")
@Transactional
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    @Autowired
    Cart cart;

    @Override
    public void addItemsToCart(Item item, Integer quantity, User currentUser) {
        if (cart.getCartOwner() == null) {
            cart.setCartOwner(currentUser.getSsoId());
        }
        if (cart.getCartOwner().equals(currentUser.getSsoId())) {
            for (int i = 0; i < quantity; i++) {
                cart.addItems(item);
            }
        }
    }

    @Override
    public List<Item> getItemsInCart(User user){
        if(cart.getCartOwner().equals(user.getSsoId())){
            return cart.getItemsList();
        } else {
            return null;
        }
    }

    @Override
    public BigInteger getItemsValue() {
        return cart.getAllItemsPrice();
    }

    @Override
    public void removeItem(User user, Integer id) {
        if(cart.getCartOwner().equals(user.getSsoId())){
            cart.removeItem(id);
        }
    }
}

