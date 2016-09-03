package com.mprtcz.webshop.controller;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.service.itemservice.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Azet on 2016-09-02.
 */
@Controller
@SessionAttributes("roles")
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * This method will list all existing items.
     */
    @RequestMapping(value = {"/items" }, method = RequestMethod.GET)
    public String listItems(ModelMap model) {

        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("loggedinuser", getPrincipal());
        return "itemslist";
    }

    @RequestMapping(value = "/item/add", method = RequestMethod.GET)
    public String addItemPage(ModelMap model) {
        Item item = new Item();
        model.addAttribute("item", item);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "additem";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/item/add" }, method = RequestMethod.POST)
    public String saveUser(@Valid Item item, BindingResult result,
                           ModelMap model) {

        System.out.println("Item to persist: " + item.toString());

        if (result.hasErrors()) {
            System.out.println("errors with result: " +result.toString());
            return "additem";
        }

        itemService.saveItem(item);

        model.addAttribute("success", "Item " + item.getItemName() + " for "+ item.getPrice() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "additemsuccess";
    }

    @RequestMapping(value = { "/item/{id}/delete" }, method = RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/item/{id}/edit" }, method = RequestMethod.GET)
    public String editItem(@PathVariable Integer id, ModelMap model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "additem";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/item/{id}/edit" }, method = RequestMethod.POST)
    public String updateItem(@Valid Item item, BindingResult result,
                             ModelMap model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            return "additem";
        }

        System.out.println("Item to persist: " + item.toString());
        itemService.updateItem(item);

        model.addAttribute("success", "Item " + item.getItemName() + " for "+ item.getPrice() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "additemsuccess";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
