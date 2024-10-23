package com.example.episafezone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "shared_with")
public class SharedWith {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tutorSharing;
    private Integer tutorReceiving;
    private Integer patient;

    public SharedWith(Integer tutorSharing, Integer tutorReceiving, Integer patient) {
        this.tutorSharing = tutorSharing;
        this.tutorReceiving = tutorReceiving;
        this.patient = patient;
    }

    public SharedWith(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTutorSharing() {
        return tutorSharing;
    }

    public void setTutorSharing(Integer tutorSharing) {
        this.tutorSharing = tutorSharing;
    }

    public Integer getTutorReceiving() {
        return tutorReceiving;
    }

    public void setTutorReceiving(Integer tutorReceiving) {
        this.tutorReceiving = tutorReceiving;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
