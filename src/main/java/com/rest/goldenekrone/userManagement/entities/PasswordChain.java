package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "password_chain")
public class PasswordChain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private Long id;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date date;

    //foreign keys

    @Column(nullable = false)
    private Long k_user;

    public Long getK_user() {
        return k_user;
    }

    public void setK_user(Long k_user) {
        this.k_user = k_user;
    }

    public PasswordChain() {}

    public PasswordChain(Long id, String password, Status status, Date date) {
        this.id = id;
        this.password = password;
        this.status = status;
        this.date = date;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PasswordChain{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
