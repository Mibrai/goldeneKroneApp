package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdressRepository extends CrudRepository<Adress, Long> {
    Optional<Adress> findAllById(Long id);
    Long countById(Long id);
}
