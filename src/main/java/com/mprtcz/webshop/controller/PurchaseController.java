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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Locale;

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

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/item/{id}/purchase"}, method = RequestMethod.GET)
    public String prepareItemPurchase(@PathVariable Integer id, ModelMap model) {
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
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
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
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
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
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
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "addtocart";
        }

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);
        Item item = itemService.findById(purchase.getItemId());
        Integer quantity = purchase.getQuantity();
        BigInteger quantityBBigInt = BigInteger.valueOf(purchase.getQuantity());

        if (item.getStock().compareTo(quantityBBigInt) > 0) {
            FieldError ssoError = new FieldError("item", "stock", messageSource.getMessage(
                    "quantity.too.large", new String[]{String.valueOf(item.getStock())}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        cartService.addItemsToCart(item, quantity, currentUser);

        modelMap.addAttribute("cartItems", cartService.getItemsInCart(currentUser));
        modelMap.addAttribute("itemsValue", cartService.getItemsValue());
        modelMap.addAttribute("accountBalance", currentUser.getBalance());

        return "cart";
    }
}
