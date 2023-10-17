package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.User;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import com.rest.goldenekrone.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired  private UserRepository userRepository;

    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findAllById(id);

        if( user.isPresent())
            return user.get();

        throw  new UserNotFoundException("User with the given ID not found");
    }

    public void deleteById(Long id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with ID "+ id);
        }

        userRepository.deleteById(id);
    }

    public void save(User user) throws UserNotFoundException {
        if(user.getId() == null && (user.getUsername() == null || user.getUsername() == "") && (user.getPassword() == null || user.getPassword() == ""))
            throw new UserNotFoundException("Given User is empty");

        userRepository.save(user);
    }

}
