package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.PasswordChain;
import com.rest.goldenekrone.userManagement.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordChainRepository extends CrudRepository<PasswordChain, Long> {
    Long countById(Long id);

    Optional<PasswordChain> findAllById(Long id);
}
