package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = true)
    private Date birthday;

    @Column(length = 2, columnDefinition = "integer default 1")
    private int acces;


    //foreign keys

    @Column(nullable = true)
    private Long k_adress;

    public User() {}

    public User(Long id, String username, String password,
                String firstname, String lastname, String email,
                Date birthday, int acces, Long k_adress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
        this.acces = acces;
        this.k_adress = k_adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAcces() {
        return acces;
    }

    public void setAcces(int acces) {
        this.acces = acces;
    }

    public Long getK_adress() {
        return k_adress;
    }

    public void setK_adress(Long k_adresse) {
        this.k_adress = k_adresse;
    }

    public User compareAndFillUser(User user){
        this.firstname = (user.getFirstname() != null && user.getFirstname() != "") ? user.getFirstname() : firstname;
        this.lastname = (user.getLastname() != null && user.getLastname() != "") ? user.getLastname() : lastname;
        this.password = (user.getPassword() != null && user.getPassword() != "" ) ? user.getPassword() : password;
        this.email = (user.getEmail() != null && user.getEmail() != "") ? user.getEmail() : email;
        this.birthday = (user.getBirthday() != null) ? user.getBirthday() : birthday;
        this.acces = (user.getAcces() != 0) ? user.getAcces() : acces;
        this.k_adress = (user.getK_adress() != null) ? user.getK_adress() : k_adress;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", acces=" + acces +
                ", k_adress=" + k_adress +
                '}';
    }
}
