package com.rest.goldenekrone.controllers;

import com.rest.goldenekrone.userManagement.Services.UserService;
import com.rest.goldenekrone.userManagement.entities.Adress;
import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;


@Controller
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/createUser")
    private String loadFormAddUser(User user){

        return "formAddUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    private String saveUser(User user) throws UserNotFoundException {
        System.out.println(user.toString());
        userService.save(user);

        return "/";

    }

    @GetMapping("/addAdress")
    private String loadFormAdress(RedirectAttributes ra, User user){
        ra.addAttribute("currentUsername",user.getUsername());
        return "/formAddAdress";
    }
    @RequestMapping(value = "/saveAdress", method = RequestMethod.POST)
    private String saveAdress(Adress adress){
        return "";
    }
}
