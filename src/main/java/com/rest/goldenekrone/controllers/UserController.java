package com.rest.goldenekrone.controllers;

import com.rest.goldenekrone.userManagement.Services.AdressService;
import com.rest.goldenekrone.userManagement.Services.UserService;
import com.rest.goldenekrone.userManagement.entities.Adress;
import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.exceptions.AdressNotFoundException;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired private UserService userService;
    @Autowired private AdressService adressService;
    public static  User currentUser;

    @GetMapping("/users")
    private String loadListUser(Model model){
        List<User> listUsers = (List<User>) userService.getAllUser();
        model.addAttribute("listUsers",listUsers);
        return "usersView";
    }
    @GetMapping("/createUser")
    private String loadFormAddUser(User user,Model model){
        model.addAttribute("user",user);
        return "formAddUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    private String saveUser(User user) throws UserNotFoundException {
        if(user.getId() == null)
            userService.save(user);
        else{
            user = currentUser.compareAndFillUser(user);
            userService.update(user);
        }


        return "redirect:/users";

    }

    @GetMapping("/addAdress")
    private String loadFormAdress(RedirectAttributes ra, User user,Adress adress){
        ra.addAttribute("currentUsername",user.getUsername());
        return "/formAddAdress";
    }
    @RequestMapping(value = "/saveAdress", method = RequestMethod.POST)
    private String saveAdress(Adress adress) throws AdressNotFoundException, UserNotFoundException {
        if(currentUser.getId() != null){
            Adress savedAdress = adressService.save(adress);
            if(savedAdress.getId() != null ) {
                currentUser.setK_adress(savedAdress.getId());
                userService.save(currentUser);
            }
        }

        return "redirect:/users";
    }

    @RequestMapping(value = "/viewUser/{id}", method = RequestMethod.GET)
    private String viewUser(@PathVariable("id") Long id, Model model, Adress adress) throws UserNotFoundException, AdressNotFoundException {
        User user = userService.getUserById(id);
        currentUser = user;
        if (user.getK_adress() != null && user.getK_adress() != 0){
            Adress userAdress = adressService.getAdressById(user.getK_adress());
            model.addAttribute("adress", userAdress);
        }
        model.addAttribute("user",user);

        return "/userProfil";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLoginPage(Model model){
        model.addAttribute("message","");
        return "login";
    }

    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public String processLogin(@RequestParam(value = "username",required = true) String username,
                               @RequestParam(value = "password", required = true) String password,
    Model model) {

        User user = new User();
        String message = "";
        try{
             user = userService.getUserByUsername(username);
        }catch (UserNotFoundException e){
            message = "Username or Password is invalid.";
        }

        if(user.getId() != null && user.getId() != 0) {
            // create session here
            return "redirect:/users";
        }

        model.addAttribute("message",message);
        return "/login";
    }
}
