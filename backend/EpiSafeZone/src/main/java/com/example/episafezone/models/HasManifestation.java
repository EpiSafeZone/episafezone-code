package com.example.episafezone.models;

import jakarta.persistence.*;

@Entity(name="has_manifestation")
public class HasManifestation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer manifestation;

    public Integer patient;

    public HasManifestation(Integer manifestation, Integer patient) {
        this.manifestation = manifestation;
        this.patient = patient;
    }
    public HasManifestation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManifestation() {
        return manifestation;
    }

    public void setManifestation(Integer manifestation) {
        this.manifestation = manifestation;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
