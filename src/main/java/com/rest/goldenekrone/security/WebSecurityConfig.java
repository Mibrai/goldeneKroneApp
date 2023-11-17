package com.rest.goldenekrone.security;

import com.rest.goldenekrone.userManagement.Services.UserService;
import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.entities.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired  UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/createUser").permitAll()
                        .requestMatchers("/saveUser").permitAll()
                        .requestMatchers("/viewUser/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((log) -> log
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                        .deleteCookies("JSESSIONID"));

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        Iterable<User> listUser = userService.getAllUser();
        List<UserDetails> listUserDetails = new ArrayList<>();



        for(User user : listUser){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(String.valueOf(UserGroup.valueOf(UserGroup.USER.matchGroup(user.getAcces()))))
                    .build();

            listUserDetails.add(userDetails);
            System.out.println(userDetails);
        }
        return new InMemoryUserDetailsManager(listUserDetails);
    }

    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
