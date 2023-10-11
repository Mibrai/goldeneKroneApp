package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.Pwd_Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Pwd_StatusRepository  extends CrudRepository<Pwd_Status,Long> {
    Optional<Pwd_Status> findAllById(Long id);

    Long countById(Long id);
}
