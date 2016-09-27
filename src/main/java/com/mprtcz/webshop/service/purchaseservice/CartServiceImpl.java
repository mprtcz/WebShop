package com.mprtcz.webshop.service.purchaseservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.purchasemodel.Cart;
import com.mprtcz.webshop.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Azet on 2016-09-10.
 */
@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    Cart cart;

    @Autowired
    PrincipalService principalService;

    @Override
    public void addItemsToCart(Item item, Integer quantity) {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            cart.addItems(item, quantity);
        }
    }

    @Override
    public Map<Item, Integer> getItemsInCart() {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            return cart.getItemsMap();
        } else {
            System.out.println("Principal:" +principalService.getPrincipal());
            return null;
        }
    }

    @Override
    public BigInteger getItemsValue() {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        return cart.getAllItemsPrice();
    }

    @Override
    public void removeItem(Integer id) {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        System.out.println("CartServiceImpl.removeItem");
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            cart.removeItem(id);
        }
    }

    private void setCartOwner() {
        if (cart.getCartOwner() == null) {
            cart.setCartOwner(principalService.getPrincipal());
        }
    }
}

