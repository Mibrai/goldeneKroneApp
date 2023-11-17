package com.rest.goldenekrone.controllers;

import com.rest.goldenekrone.userManagement.Services.AdressService;
import com.rest.goldenekrone.userManagement.Services.UserService;
import com.rest.goldenekrone.userManagement.entities.Adress;
import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.exceptions.AdressNotFoundException;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UserController {
    @Autowired private UserService userService;
    @Autowired private AdressService adressService;

    public static  User currentUser;
    public static HttpSession currentSession;

    @GetMapping("/users")
    private String loadListUser(Model model, HttpSession session, Authentication authentication){
        List<User> listUsers = (List<User>) userService.getAllUser();
        model.addAttribute("listUsers",listUsers);
        model.addAttribute("currentUser",session.getAttribute("username"));

        authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName()+"\n"+authentication.getAuthorities().toString()+"\n"+authentication.getDetails().toString());
        return "usersView";
    }
    @GetMapping("/createUser")
    private String loadFormAddUser(User user,Model model){
        model.addAttribute("user",user);
        return "formAddUser";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    private String saveUser(User user, HttpServletRequest request) throws UserNotFoundException, Exception {

        if(user.getId() == null)
            userService.save(user);
        else{
            user = currentUser.compareAndFillUser(user);
            userService.update(user);
        }

        request.logout();
        request.login(user.getUsername(),user.getPassword());
        return "redirect:/users";

    }

    @GetMapping("/addAdress")
    private String loadFormAdress(RedirectAttributes ra, User user,Adress adress){
        ra.addAttribute("currentUsername",user.getUsername());
        return "/formAddAdress";
    }
    @RequestMapping(value = "/saveAdress", method = RequestMethod.POST)
    private String saveAdress(Adress adress) throws AdressNotFoundException, UserNotFoundException, Exception {
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
                                HttpServletRequest request,
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
            currentSession = request.getSession();
            currentSession.setAttribute("username",user.getUsername());
            currentSession.setAttribute("acces",user.getAcces());
            return "redirect:/users";
        }

        model.addAttribute("message",message);
        return "/login";
    }

    /*
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String closeSession(){
        currentSession.removeAttribute("username");
        currentSession.removeAttribute("acces");
        currentSession.invalidate();
        return "redirect:/login";
    } */

}
