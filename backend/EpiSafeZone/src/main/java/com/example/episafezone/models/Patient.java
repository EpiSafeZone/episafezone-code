package com.example.episafezone.models;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import jakarta.persistence.*;
import com.example.episafezone.services.NotificationService;

import java.util.Date;
import java.util.List;
import com.example.episafezone.services.SharedWithService;

@Entity (name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private Integer height;
    private Integer weight;
    private Date birthdate;
    private Integer age;
    @Column(name = "color")
    private String imageUrl;

    public Patient(String name, String surname, Integer height, Integer weight, Date birthdate, Integer age, String imageUrl) {
        this.name = name;
        this.surname = surname;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public Patient (){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Tutor> getTutors() {
        SharedWithService sharedWithService = SpringContext.getBean(SharedWithService.class);
        return sharedWithService.findPatientTutors(this.id);
    }

    public void triggerEvent(Event event){
        NotificationService.TriggerNotifications(event, this);
    }
}
