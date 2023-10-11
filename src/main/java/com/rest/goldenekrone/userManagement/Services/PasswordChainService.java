package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.PasswordChain;
import com.rest.goldenekrone.userManagement.exceptions.PasswordChainNotFoundException;
import com.rest.goldenekrone.userManagement.repository.PasswordChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordChainService {
    @Autowired
    private PasswordChainRepository passwordChainRepository;

    private Iterable<PasswordChain> getAllPasswordChain(){
        return passwordChainRepository.findAll();
    }

    private PasswordChain getPasswordChainById(Long id) throws PasswordChainNotFoundException {
        Optional<PasswordChain> passwordChain = passwordChainRepository.findAllById(id);

        if( passwordChain.isPresent())
            return passwordChain.get();

        throw  new PasswordChainNotFoundException("PasswordChain with the given ID not found");
    }

    private void deleteById(Long id) throws PasswordChainNotFoundException {
        Long count = passwordChainRepository.countById(id);
        if(count == null || count == 0){
            throw new PasswordChainNotFoundException("Could not find any PasswordChain with ID "+ id);
        }

        passwordChainRepository.deleteById(id);
    }
}
