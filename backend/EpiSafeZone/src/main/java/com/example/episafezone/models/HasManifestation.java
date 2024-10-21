package com.example.episafezone.models;

import jakarta.persistence.*;

@Entity(name="has_manifestation")
public class HasManifestation {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer manifestation;

    public Integer patient;

    public HasManifestation(Integer id, Manifestation manifestation, Patient patient) {
        this.id = id;
        this.manifestation = manifestation.getId();
        this.patient = patient.getId();
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

    public void setManifestation(Manifestation manifestation) {
        this.manifestation = manifestation.getId();
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient.getId();
    }
}
