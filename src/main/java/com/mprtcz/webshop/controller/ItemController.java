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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
        String imageAddress = "";
        model.addAttribute("item", item);
        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("imageAddress", imageAddress);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "additem";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/item/add"}, method = RequestMethod.POST)
    public String saveItem(ModelMap model, @Valid @ModelAttribute("fileBucket") FileBucket fileBucket, BindingResult result2,
                           @Valid Item item, BindingResult result, String imageAddress) throws IOException {

        System.out.println("Item to persist: " + item.toString());
        System.out.println("Image address: " +imageAddress);

        if (result.hasErrors() || result2.hasErrors()) {
            model.addAttribute("isfileerror", true);
            model.addAttribute("errorMsg", result2.getFieldError().getCode());
            return "additem";
        }

        itemService.saveItem(item);

        if(fileBucket.getFile().isEmpty()){
            if(!Objects.equals(imageAddress, ""))
            imageService.saveLinkedImage(imageAddress, item);
        } else {
            imageService.saveUploadedImage(fileBucket, item);
        }

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

        imageService.saveUploadedImage(fileBucket, item);

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

    @RequestMapping("/item/{id}/image")
    public void getItemImage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Item item = itemService.findById(id);

        byte[] image = null;

        String imagePath = imageService.getImagePathByItemId(item.getId());

        System.out.println(imagePath);

        // open a file and read it as a byte array
        image = Files.readAllBytes(Paths.get(imagePath));


        OutputStream out = response.getOutputStream();
        response.setContentType("image/png");

        out.write(image);
        out.flush();
        out.close();
    }
}
