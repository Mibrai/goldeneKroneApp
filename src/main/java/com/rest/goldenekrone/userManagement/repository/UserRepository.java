package com.rest.goldenekrone.userManagement.repository;

import com.rest.goldenekrone.userManagement.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findAllById(Long id);

     Long countById(Long id);

    @Query(value = "UPDATE user u SET u.acces = :acces, u.birthday = :birthday, u.email = :email, " +
            "u.firstname = :firstname, u.lastname = :lastname, u.k_adress = :k_adress, " +
            "u.password = :password WHERE u.user_id = :id", nativeQuery = true)
     void updateUserById(@Param("acces") int acces, @Param("birthday")Date birthday,
                               @Param("email") String email, @Param("firstname") String firstname, @Param("lastname") String lastname,
                               @Param("k_adress") Long k_adress, @Param("password") String password,@Param("id") Long id);
    @Query(value = "SELECT * FROM user u WHERE u.username = :username ",nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);
}
