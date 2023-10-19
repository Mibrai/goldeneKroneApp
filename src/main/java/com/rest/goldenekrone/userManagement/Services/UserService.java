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

    public void update(User user) throws UserNotFoundException{
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

                if(userRepository.updateUserById(tmp_user.get().getId(),tmp_user.get().getAcces(),
                        tmp_user.get().getBirthday(),tmp_user.get().getEmail(),tmp_user.get().getFirstname(),
                        tmp_user.get().getLastname(),tmp_user.get().getK_adress(),
                        tmp_user.get().getPassword() ).getId() == null)
                    throw  new UserNotFoundException("Can't update user");
            } else throw new UserNotFoundException("User not found");
        }

    }

}
