package com.example.episafezone.DTO.SharedDTO;

import java.io.Serializable;

public class IsTutorDTO implements Serializable {

    private Integer tutor;
    private Integer patient;

    public IsTutorDTO(Integer tutor, Integer patient) {
        this.tutor = tutor;
        this.patient = patient;
    }
    public IsTutorDTO(){}

    public Integer getTutor() {
        return tutor;
    }

    public void setTutor(Integer tutor) {
        this.tutor = tutor;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
