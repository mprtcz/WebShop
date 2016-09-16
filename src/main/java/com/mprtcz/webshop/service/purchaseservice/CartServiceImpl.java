package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.purchasemodel.Cart;
import com.mprtcz.webshop.model.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
@Service("cartService")
@Transactional
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService , HttpSessionListener {

    @Autowired
    Cart cart;

    @Override
    public void addItemsToCart(Item item, Integer quantity, User currentUser) {
        if (cart.getCartOwner() == null) {
            cart.setCartOwner(currentUser.getSsoId());
        }
        if (cart.getCartOwner().equals(currentUser.getSsoId())) {
            cart.addItems(item, quantity);
        }
    }

    @Override
    public Map<Item, Integer> getItemsInCart(User user) {
        System.out.println("user.getSsoId() = " + user.getSsoId());
        System.out.println("cart.getCartOwner() = " + cart.getCartOwner());
        if (cart.getCartOwner().equals(user.getSsoId())) {
            System.out.println("CartServiceImpl.getItemsInCart");
            System.out.println("cart.getItemsList() = " + cart.getItemsList());
            return cart.getItemsList();
        } else {
            System.out.println("CartServiceImpl.getItemsInCart");
            System.out.println("null");
            return null;
        }
    }

    @Override
    public BigInteger getItemsValue() {
        return cart.getAllItemsPrice();
    }

    @Override
    public void removeItem(User user, Integer id) {
        if (cart.getCartOwner().equals(user.getSsoId())) {
            cart.removeItem(id);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("CartServiceImpl.sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("CartServiceImpl.sessionDestroyed");
    }
}

