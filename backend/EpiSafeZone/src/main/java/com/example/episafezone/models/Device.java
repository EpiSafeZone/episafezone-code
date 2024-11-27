package com.example.episafezone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Integer user;

    public Device(String token, Integer user) {
        this.token = token;
        this.user = user;
    }

    public Device(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}

