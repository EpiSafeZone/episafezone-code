package com.example.episafezone.DTO.SharedDTO;

import java.io.Serializable;

public class GetPermissionsDTO implements Serializable {

    private Integer tutorReceiving;
    private Integer patient;

    public GetPermissionsDTO(Integer tutorReceiving, Integer patient) {
        this.tutorReceiving = tutorReceiving;
        this.patient = patient;
    }

    public GetPermissionsDTO(){}

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
