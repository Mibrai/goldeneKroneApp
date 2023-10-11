package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @Column(length = 100,nullable = false,unique = true)
    private String token;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    public Session(){}

    public Session(Long id, String token, LocalDateTime date) {
        this.id = id;
        this.token = token;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", date=" + date +
                '}';
    }
}
