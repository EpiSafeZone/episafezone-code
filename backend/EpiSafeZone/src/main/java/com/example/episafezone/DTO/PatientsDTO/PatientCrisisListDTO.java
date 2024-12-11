package com.example.episafezone.DTO.PatientsDTO;

import java.io.Serializable;
import java.util.List;

public class PatientCrisisListDTO implements Serializable {
    private List<PatientCrisisDTO> lista;

    public PatientCrisisListDTO(List<PatientCrisisDTO> lista) {
        this.lista = lista;
    }

    public PatientCrisisListDTO(){}

    public List<PatientCrisisDTO> getLista() {
        return lista;
    }

    public void setLista(List<PatientCrisisDTO> lista) {
        this.lista = lista;
    }
}
