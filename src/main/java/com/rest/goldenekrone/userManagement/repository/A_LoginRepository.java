package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.A_Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface A_LoginRepository extends CrudRepository<A_Login, Long> {
    Long countById(Long id);
}
