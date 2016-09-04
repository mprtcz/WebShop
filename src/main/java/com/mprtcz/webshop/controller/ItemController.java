package com.mprtcz.webshop.controller;

import com.mprtcz.webshop.model.itemmodel.FileBucket;
import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.service.itemservice.ImageService;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.validators.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Azet on 2016-09-02.
 */
@Controller
@SessionAttributes("roles")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ImageService imageService;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    /**
     * This method will list all existing items.
     */
    @RequestMapping(value = {"/items"}, method = RequestMethod.GET)
    public String listItems(ModelMap model) {

        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("loggedinuser", getPrincipal());
        return "itemslist";
    }

    @RequestMapping(value = "/item/add", method = RequestMethod.GET)
    public String addItemPage(ModelMap model) {
        Item item = new Item();
        FileBucket fileModel = new FileBucket();
        model.addAttribute("item", item);
        model.addAttribute("edit", false);
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("loggedinuser", getPrincipal());
        return "additem";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/item/add"}, method = RequestMethod.POST)
    public String saveItem(ModelMap model, @Valid @ModelAttribute("fileBucket") FileBucket fileBucket, BindingResult result2,
                           @Valid Item item, BindingResult result) throws IOException {

        System.out.println("Item to persist: " + item.toString());

        if (result.hasErrors() || result2.hasErrors()) {
            model.addAttribute("isfileerror", true);
            model.addAttribute("errorMsg", result2.getFieldError().getCode());
            return "additem";
        }

        itemService.saveItem(item);

        imageService.saveImage(fileBucket, item);

        model.addAttribute("success", "Item " + item.getItemName() + " for " + item.getPrice() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "additemsuccess";
    }

    @RequestMapping(value = {"/item/{id}/delete"}, method = RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItemById(id);
        imageService.deleteImage(id);
        return "redirect:/items";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/item/{id}/edit"}, method = RequestMethod.GET)
    public String editItem(@PathVariable Integer id, ModelMap model) {
        Item item = itemService.findById(id);
        FileBucket fileModel = new FileBucket();

        model.addAttribute("item", item);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("fileBucket", fileModel);
        return "additem";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/item/{id}/edit"}, method = RequestMethod.POST)
    public String updateItem(@Valid @ModelAttribute("fileBucket") FileBucket fileBucket, BindingResult result2,
                             @Valid Item item, BindingResult result,
                             ModelMap model, @PathVariable Integer id) throws IOException {

        if (result.hasErrors() || result2.hasErrors()) {
            model.addAttribute("isfileerror", true);
            model.addAttribute("errorMsg", result2.getFieldError().getCode());
            return "additem";
        }

        itemService.updateItem(item);

        imageService.saveImage(fileBucket, item);

        model.addAttribute("success", "Item " + item.getItemName() + " for " + item.getPrice() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "additemsuccess";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();

        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public void init(){
        System.out.println("SERVLET INITIALIZED");
    }

}
