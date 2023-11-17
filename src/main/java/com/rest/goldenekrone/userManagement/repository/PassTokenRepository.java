package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.PassToken;
import com.rest.goldenekrone.userManagement.entities.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassTokenRepository extends CrudRepository<PassToken,Long> {

    Optional<PassToken> findById(Long id);
    @Query(value = "SELECT * FROM passtoken WHERE status = ?2 AND userId = ?1",nativeQuery = true)
    PassToken findByUserByState(Long id, Status status);

    @Query(value = "UPDATE passtoken SET status = 'PASSIF' WHERE userId = ?",nativeQuery = true)
    void setAllTokenInactiveByUser(Long userId);
}
