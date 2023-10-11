package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Optional<Session> findAllById(Long id);
    Long countById(Long id);
}
