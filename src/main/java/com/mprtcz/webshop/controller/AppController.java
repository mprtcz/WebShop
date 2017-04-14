package com.mprtcz.webshop.controller;

import com.mprtcz.webshop.model.itemmodel.Item;
import com.mprtcz.webshop.model.usermodel.User;
import com.mprtcz.webshop.model.usermodel.UserProfile;
import com.mprtcz.webshop.service.itemservice.ItemService;
import com.mprtcz.webshop.service.security.PrincipalService;
import com.mprtcz.webshop.service.userservice.UserProfileService;
import com.mprtcz.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by Azet on 2016-08-27.
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {
    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    PrincipalService principalService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    ItemService itemService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        List<Item> itemsList = itemService.getRandomItems(6);
        model.addAttribute("isanonymus", principalService.isCurrentAuthenticationAnonymous());
        model.addAttribute("itemslist", itemsList);
        return "index";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/items";
        }
    }

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        return "userslist";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registrationPage(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        model.addAttribute("userProfileType", "2");
        return "registration";
    }


    private Integer getUserId() {
        Integer userID = -1;
        userID = userService.findBySSO(principalService.getPrincipal()).getId();
        return userID;
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId",
                    messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        return "accessDenied";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);

        }
        return "redirect:/login?logout";
    }


    @RequestMapping(value = {"/user/{ssoId}/delete"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/users";
    }

    @RequestMapping(value = {"/user/{ssoId}"}, method = RequestMethod.GET)
    public String viewUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        String currentUserName = principalService.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("currentUserName", currentUserName);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        return "userprofile";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String viewCurrentUser(ModelMap model) {
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return loginPage();
        } else {
            return viewUser(principalService.getPrincipal(), model);
        }
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/user/{ssoId}/edit"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/user/{ssoId}/edit"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", principalService.getPrincipal());
        return "registrationsuccess";
    }


    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
}
