package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.A_Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface A_LoginRepository extends CrudRepository<A_Login, Long> {
    Long countById(Long id);

    @Query(value = "SELECT * FROM a_login a WHERE a.k_user = :userId ", nativeQuery = true)
    A_Login getAloginByUser(@Param("userId") Long userId);
}
