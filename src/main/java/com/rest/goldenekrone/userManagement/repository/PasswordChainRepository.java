package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.PasswordChain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PasswordChainRepository extends CrudRepository<PasswordChain, Long> {
    Long countById(Long id);

    Optional<PasswordChain> findAllById(Long id);

    @Query(value = "SELECT * FROM password_chain p WHERE p.k_user = :userId ", nativeQuery = true)
    Collection<PasswordChain> findAllByUserId(@Param("userId") Long userId);


    @Query(value = "SELECT * FROM password_chain p WHERE p.k_user = :userId AND p.password = :userPassword ", nativeQuery = true)
    PasswordChain findSingleByUserId(@Param("userId") Long userId,@Param("userPassword") String userPassword);
}
