package com.example.episafezone.DTO.PatientsDTO;

import java.io.Serializable;
import java.util.Date;

public class PatientCrisisDTO implements Serializable {
    private String  dia;
    private Integer numCrisis;

    public PatientCrisisDTO(String dia, Integer numCrisis) {
        this.dia = dia;
        this.numCrisis = numCrisis;
    }

    public PatientCrisisDTO(){}

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Integer getNumCrisis() {
        return numCrisis;
    }

    public void setNumCrisis(Integer numCrisis) {
        this.numCrisis = numCrisis;
    }
}
