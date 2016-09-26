package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.purchasemodel.Cart;
import com.mprtcz.webshop.service.purchaseservice.CartService;
import com.mprtcz.webshop.service.purchaseservice.CartServiceImpl;
import com.mprtcz.webshop.service.security.PrincipalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Created by Azet on 2016-09-26.
 */
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {


    @InjectMocks
    CartService cartService = new CartServiceImpl();

    @Mock
    Cart cart;

    @Mock
    PrincipalService principalService;

    @BeforeMethod
    private void init() {
        MockitoAnnotations.initMocks(this);

    }


    private Map<Item, Integer> createItemsMap(){
        Map<Item, Integer> itemsMap = new HashMap<>();
        Item item = new Item();
        item.setItemName("Item1 name");
        item.setId(1);
        item.setPrice(BigInteger.ONE);
        item.setDescription("Item1 Description");
        itemsMap.put(item, 1);

        Item item2 = new Item();
        item2.setItemName("Item2 name");
        item2.setId(1);
        item2.setPrice(BigInteger.ONE);
        item2.setDescription(" Item2 Description");
        itemsMap.put(item2, 2);

        return itemsMap;
    }


    @Test
    public void itemsMapTest(){
        when(cart.getItemsList()).thenReturn(createItemsMap());
        when(cart.getCartOwner()).thenReturn("Azot");
        when(principalService.getPrincipal()).thenReturn("Azot");

        assertEquals(cartService.getItemsInCart() , cart.getItemsList());
    }
}
