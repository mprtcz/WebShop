package com.mprtcz.webshop.controller;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.purchasemodel.Purchase;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.purchaseservice.CartService;
import com.mprtcz.webshop.service.purchaseservice.PurchaseService;
import com.mprtcz.webshop.service.security.PrincipalService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Azet on 2016-09-08.
 */
@Controller
public class PurchaseController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PrincipalService principalService;

    @Autowired
    CartService cartService;

    @RequestMapping(value = {"/item/{id}/purchase"}, method = RequestMethod.GET)
    public String prepareItemPurchase(@PathVariable Integer id, ModelMap model) {

        Item item = itemService.findById(id);
        String userName = principalService.getPrincipal();
        User user = userService.findBySSO(userName);
        Purchase purchase = new Purchase();
        System.out.println("Purchase Item: " + item.toString());
        System.out.println("Purchase user: " + user.toString());
        model.addAttribute("purchase", purchase);
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return "confirmpurchase";
    }

    @RequestMapping(value = {"/item/{id}/purchase"}, method = RequestMethod.POST)
    public String purchaseItems(@PathVariable Integer id, @Valid Purchase purchase,
                                BindingResult result, ModelMap model) {

        System.out.println("PurchaseController.purchaseItems");
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "confirmpurchase";
        }

        //TODO other errors

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);
        Item item = itemService.findById(purchase.getItemId());
        Integer quantity = purchase.getQuantity();

        System.out.println("quantity = " + quantity);
        System.out.println("item = " + item);
        System.out.println("currentUser = " + currentUser);

        String purchaseResult = purchaseService.purchase(currentUser, item, quantity);

        model.addAttribute("item", item);
        model.addAttribute("quantity", purchase.getQuantity());
        model.addAttribute("user", currentUser);
        if (purchaseResult.equals("success")) {
            return "purchasesuccess";
        } else {
            model.addAttribute("purchaseResult", purchaseResult);
            return "confirmpurchase";
        }
    }

    @RequestMapping(value = {"/item/{id}/addtocart"}, method = RequestMethod.GET)
    public String addItemToCart(@PathVariable Integer id, ModelMap model) {

        Item item = itemService.findById(id);
        String userName = principalService.getPrincipal();
        User user = userService.findBySSO(userName);
        Purchase purchase = new Purchase();
        System.out.println("Purchase Item: " + item.toString());
        System.out.println("Purchase user: " + user.toString());
        model.addAttribute("purchase", purchase);
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return "addtocart";

    }

    @RequestMapping(value = {"item/{id}/addtocart"}, method = RequestMethod.POST)
    public String addToCart(@Valid Purchase purchase, BindingResult result,
                            ModelMap modelMap) {

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);
        Item item = itemService.findById(purchase.getItemId());
        Integer quantity = purchase.getQuantity();

        cartService.addItemsToCart(item, quantity, currentUser);

        modelMap.addAttribute("cartItems", cartService.getItemsInCart(currentUser));

        return "addedtocart";
    }
}
