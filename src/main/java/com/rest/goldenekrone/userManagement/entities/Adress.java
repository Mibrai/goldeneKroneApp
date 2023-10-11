package com.rest.goldenekrone.userManagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "adress")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private Long id;

    @Column(length = 50, nullable = true)
    private String city;

    @Column(length = 50, nullable = true)
    private String land;

    @Column(length = 50, nullable = true)
    private String quater;

    @Column(length = 50, nullable = true)
    private String phoneNumber;

    @Column(length = 60, nullable = true)
    private String street;

    public Adress(){}
    public Adress(Long id, String city, String land,
                  String quater, String phoneNumber, String street) {
        this.id = id;
        this.city = city;
        this.land = land;
        this.quater = quater;
        this.phoneNumber = phoneNumber;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getQuater() {
        return quater;
    }

    public void setQuater(String quater) {
        this.quater = quater;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", land='" + land + '\'' +
                ", quater='" + quater + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
