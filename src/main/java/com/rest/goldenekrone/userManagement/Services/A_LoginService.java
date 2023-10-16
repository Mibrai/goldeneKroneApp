package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.A_Login;
import com.rest.goldenekrone.userManagement.exceptions.A_LoginNotFoundException;
import com.rest.goldenekrone.userManagement.repository.A_LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class A_LoginService {

    @Autowired private A_LoginRepository aLoginRepository;

    private Iterable<A_Login> getAllALogin(){
        return aLoginRepository.findAll();
    }

    private A_Login getALoginById(Long id) throws A_LoginNotFoundException {
        Optional<A_Login> a_login = aLoginRepository.findById(id);
        if(a_login.isPresent())
            return a_login.get();

        throw new A_LoginNotFoundException("ALogin with the given ID not found");
    }

    private void deleteALoginById(Long id) throws A_LoginNotFoundException {
        Long count = aLoginRepository.countById(id);

        if(count == null || count == 0)
            throw  new A_LoginNotFoundException("ALogin with the given ID not found");

        aLoginRepository.deleteById(id);
    }

    private A_Login getAloginByUser(Long userId){
        return aLoginRepository.getAloginByUser(userId);
    }

    private void save(A_Login aLogin) throws A_LoginNotFoundException {
        if(aLogin.getId() != null)
            aLoginRepository.save(aLogin);
        else
            throw new A_LoginNotFoundException("Given ALogin is empty");
    }
}
