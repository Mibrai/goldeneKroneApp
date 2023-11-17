package com.rest.goldenekrone.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("usersView");
        registry.addViewController("/users").setViewName("usersView");
        registry.addViewController("/createUser").setViewName("formAddUser");
        registry.addViewController("/addAdress").setViewName("formAddAdress");
        registry.addViewController("/viewUser").setViewName("userProfil");
        registry.addViewController("/login").setViewName("login");
    }
}
