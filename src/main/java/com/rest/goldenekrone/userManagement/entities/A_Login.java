package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "a_login")
public class A_Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alogin_id")
    private Long id;

    @Column(nullable = false)
    private Long k_user;

    @Column(nullable = false)
    private Long k_session;

    public A_Login(){}

    public A_Login(Long id, Long k_user, Long k_session) {
        this.id = id;
        this.k_user = k_user;
        this.k_session = k_session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getK_user() {
        return k_user;
    }

    public void setK_user(Long k_user) {
        this.k_user = k_user;
    }

    public Long getK_session() {
        return k_session;
    }

    public void setK_session(Long k_session) {
        this.k_session = k_session;
    }

    @Override
    public String toString() {
        return "A_Login{" +
                "id=" + id +
                ", k_user=" + k_user +
                ", k_session=" + k_session +
                '}';
    }
}
