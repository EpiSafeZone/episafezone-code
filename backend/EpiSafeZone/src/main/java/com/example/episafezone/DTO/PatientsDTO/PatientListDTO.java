package com.example.episafezone.DTO.PatientsDTO;

import java.io.Serializable;

public class PatientListDTO implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private String color;

    public PatientListDTO(Integer id, String name, String surname, String color) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.color = color;
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
