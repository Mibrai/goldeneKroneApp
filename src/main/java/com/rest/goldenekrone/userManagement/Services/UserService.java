package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.entities.UserGroup;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import com.rest.goldenekrone.userManagement.repository.UserRepository;
import com.rest.goldenekrone.utils.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired  private UserRepository userRepository;
    @Autowired private EmailService emailService;

    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findAllById(id);

        if( user.isPresent())
            return user.get();

        throw  new UserNotFoundException("User with the given ID not found");
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        if( user.isPresent())
            return user.get();

        throw  new UserNotFoundException("User with the given Username not found");
    }

    public void deleteById(Long id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with ID "+ id);
        }

        userRepository.deleteById(id);
    }

    public void save(User user) throws UserNotFoundException, Exception {
        if(user.getId() == null && (user.getUsername() == null || user.getUsername() == "") && (user.getPassword() == null || user.getPassword() == ""))
            throw new UserNotFoundException("Given User is empty");

        userRepository.save(user);
        emailService.sendMail(user,"Account was created","Wellcome");
        //saveUserInMemory(user,http);
    }

    public void update(User user) throws UserNotFoundException, Exception {
        if(user.getId() != null){
            Optional<User> tmp_user = userRepository.findById(user.getId());
            if(tmp_user.isPresent()){
                tmp_user.get().setBirthday(user.getBirthday());
                tmp_user.get().setAcces(user.getAcces());
                tmp_user.get().setEmail(user.getEmail());
                tmp_user.get().setFirstname(user.getFirstname());
                tmp_user.get().setLastname(user.getLastname());
                tmp_user.get().setUsername(user.getUsername());
                tmp_user.get().setPassword(user.getPassword());
                tmp_user.get().setK_adress(user.getK_adress());

                userRepository.updateUserById(tmp_user.get().getAcces(),
                        tmp_user.get().getBirthday(),tmp_user.get().getEmail(),tmp_user.get().getFirstname(),
                        tmp_user.get().getLastname(),tmp_user.get().getK_adress(),
                        tmp_user.get().getPassword(),tmp_user.get().getId() );

                //saveUserInMemory(tmp_user.get(), http);
                emailService.sendMail(tmp_user.get(),"Update Profil","Your Profil was uptodate");

            } else throw new UserNotFoundException("User not found");
        }

    }

    public  void saveUserInMemory(User user, HttpSecurity http) throws Exception {

        UserDetails currentUser = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .password(String.valueOf(UserGroup.valueOf(UserGroup.USER.matchGroup(user.getAcces()))))
                .build();
        new InMemoryUserDetailsManager(currentUser);

        //Manual Authentication
       /* AuthenticationManager authManager = http.getSharedObject(AuthenticationManagerBuilder.class).build();

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);*/
        /*HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT",sc);*/

    }

}
