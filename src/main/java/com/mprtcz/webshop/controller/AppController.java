package com.mprtcz.webshop.controller;

import com.mprtcz.webshop.model.usermodel.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Azet on 2016-08-27.
 */
@Controller
@RequestMapping("/")
public class AppController {

    @RequestMapping("/")
    public String helloPage(ModelMap model) {
        return "index";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registrationPage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }
}
