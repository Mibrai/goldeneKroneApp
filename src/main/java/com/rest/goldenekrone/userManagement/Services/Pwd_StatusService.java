package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.Pwd_Status;
import com.rest.goldenekrone.userManagement.exceptions.Pwd_StatusNotFoundException;
import com.rest.goldenekrone.userManagement.repository.Pwd_StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Pwd_StatusService {
    @Autowired
    private Pwd_StatusRepository pwdStatusRepository;

    private Iterable<Pwd_Status> getAllPwdStatus(){
        return pwdStatusRepository.findAll();
    }

    private Pwd_Status getPwdStatusById(Long id) throws Pwd_StatusNotFoundException {
        Optional<Pwd_Status> pwdStatus = pwdStatusRepository.findAllById(id);

        if( pwdStatus.isPresent())
            return pwdStatus.get();

        throw  new Pwd_StatusNotFoundException("PwdStatus with the given ID not found");
    }

    private void deleteById(Long id) throws Pwd_StatusNotFoundException {
        Long count = pwdStatusRepository.countById(id);
        if(count == null || count == 0){
            throw new Pwd_StatusNotFoundException("Could not find any PwdStatus with ID "+ id);
        }

        pwdStatusRepository.deleteById(id);
    }
}
