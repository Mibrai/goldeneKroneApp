package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.Session;
import com.rest.goldenekrone.userManagement.exceptions.SessionNotFoundException;
import com.rest.goldenekrone.userManagement.exceptions.UserNotFoundException;
import com.rest.goldenekrone.userManagement.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    private Iterable<Session> getAllSession(){
        return sessionRepository.findAll();
    }

    private Session getSessionById(Long id) throws SessionNotFoundException{
        Optional<Session> session = sessionRepository.findAllById(id);

        if( session.isPresent())
            return session.get();

        throw  new SessionNotFoundException("Session with the given ID not found");
    }

    private void deleteById(Long id) throws UserNotFoundException {
        Long count = sessionRepository.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("Could not find any Session with ID "+ id);
        }

        sessionRepository.deleteById(id);
    }
}
