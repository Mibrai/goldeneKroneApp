package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "passtoken")
public class PassToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true, length = 100, columnDefinition = "VARCHAR")
    private UUID token;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime datetime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false,length = 10)
    private Long userId;

    public PassToken(int id, UUID token, LocalDateTime datetime, Status status, Long userId) {
        this.id = id;
        this.token = token;
        this.datetime = datetime;
        this.status = status;
        this.userId = userId;
    }

    public PassToken() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isValid(){
        if(this.datetime != null && this.token != null && this.userId != null
        && this.status != null)
            return true;

        return false;
    }
}
