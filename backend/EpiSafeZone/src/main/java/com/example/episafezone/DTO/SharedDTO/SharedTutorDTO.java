package com.example.episafezone.DTO.SharedDTO;

import com.example.episafezone.models.Tutor;

import java.io.Serializable;

public class SharedTutorDTO implements Serializable {
    private Integer id;
    private String name;
    private String surname;

    public SharedTutorDTO(Tutor tutor) {
        this.id = tutor.getId();
        this.name = tutor.getName();
        this.surname = tutor.getSurname();
    }

    public SharedTutorDTO(){}

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
}

