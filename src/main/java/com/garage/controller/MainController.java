package com.garage.controller;

import com.garage.model.User;
import com.garage.service.SecurityService;
import com.garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    void regUser(User user, Model model) {

        User toAddtoDB = new User();
        toAddtoDB.setUserName(user.getUserName());
        toAddtoDB.setPassword(user.getPassword());
        toAddtoDB.setRoles("ROLE_USER");
        toAddtoDB.setActive(true);
        userService.saveUser(toAddtoDB);

        securityService.autologin(user.getUserName(), user.getPassword());

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    String showregPage() {
        return "registration";
    }
}
