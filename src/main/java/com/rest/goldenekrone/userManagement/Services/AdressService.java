package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.Adress;
import com.rest.goldenekrone.userManagement.exceptions.AdressNotFoundException;
import com.rest.goldenekrone.userManagement.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdressService {
    @Autowired
    private AdressRepository adressRepository;

    private Iterable<Adress> getAllAdress(){
        return adressRepository.findAll();
    }

    private Adress getAdressById(Long id) throws AdressNotFoundException {
        Optional<Adress> adress = adressRepository.findAllById(id);

        if( adress.isPresent())
            return adress.get();

        throw  new AdressNotFoundException("Adress with the given ID not found");
    }

    private void deleteById(Long id) throws AdressNotFoundException {
        Long count = adressRepository.countById(id);
        if(count == null || count == 0){
            throw new AdressNotFoundException("Could not find any Adress with ID "+ id);
        }

        adressRepository.deleteById(id);
    }
}
