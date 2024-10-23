package com.example.episafezone.DTO.PatientsDTO;

import com.example.episafezone.models.Patient;

import java.io.Serializable;

public class PatientListDTO implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private String color;

    public PatientListDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.color = patient.getColor();
    }

    public PatientListDTO(){}

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
