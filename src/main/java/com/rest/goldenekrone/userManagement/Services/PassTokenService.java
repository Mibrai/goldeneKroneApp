package com.rest.goldenekrone.userManagement.Services;

import com.rest.goldenekrone.userManagement.entities.PassToken;
import com.rest.goldenekrone.userManagement.entities.Status;
import com.rest.goldenekrone.userManagement.exceptions.PassTokenNotFoundException;
import com.rest.goldenekrone.userManagement.repository.PassTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassTokenService {
    @Autowired private PassTokenRepository passTokenRepository;

    public PassToken getPassTokenById(Long tokenId) throws PassTokenNotFoundException {
        if(tokenId != 0){
            Optional<PassToken> passToken = passTokenRepository.findById(tokenId);
            if(passToken.isPresent() && passToken.get().isValid())
                return passTokenRepository.findById(tokenId).get();
            else
                throw new PassTokenNotFoundException("Not Valid Token Found for the given ID "+tokenId);
        }
        else
            throw new PassTokenNotFoundException("The given Id "+tokenId+" must be different to 0 ");
    }

    public PassToken getTokenByUserByStatus(Long userId, Status status) throws PassTokenNotFoundException {
        if(userId != 0){
            PassToken passToken = passTokenRepository.findByUserByState(userId,status);
            if(passToken.isValid())
                return passToken;
            else
                throw new PassTokenNotFoundException("No Token found for the given ID "+userId+" and given status"+status);
        }
        else
            throw new PassTokenNotFoundException("The given ID must be different to 0");
    }

    public void setAllTokenToInactive(Long userId){
        passTokenRepository.setAllTokenInactiveByUser(userId);
    }

    public PassToken save(PassToken passToken) throws PassTokenNotFoundException {
        if(passToken.isValid())
            return passTokenRepository.save(passToken);
        else
            throw new PassTokenNotFoundException("Can't save current Token");
    }

    public void delete(Long tokenId) throws PassTokenNotFoundException {

        passTokenRepository.delete(getPassTokenById(tokenId));
    }
}
