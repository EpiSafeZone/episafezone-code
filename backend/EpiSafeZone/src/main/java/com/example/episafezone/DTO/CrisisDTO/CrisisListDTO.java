package com.example.episafezone.DTO.CrisisDTO;

import java.io.Serializable;
import java.util.List;

public class CrisisListDTO implements Serializable {

    private List<CrisisDTO> Crisis;

    public CrisisListDTO(List<CrisisDTO> crisis) {
        Crisis = crisis;
    }

    public CrisisListDTO(){}

    public List<CrisisDTO> getCrisis() {
        return Crisis;
    }

    public void setCrisis(List<CrisisDTO> crisis) {
        Crisis = crisis;
    }
}