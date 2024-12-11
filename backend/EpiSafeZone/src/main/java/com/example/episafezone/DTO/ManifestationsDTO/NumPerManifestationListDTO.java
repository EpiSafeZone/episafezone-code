package com.example.episafezone.DTO.ManifestationsDTO;

import java.io.Serializable;
import java.util.List;

public class NumPerManifestationListDTO implements Serializable {

    private List<NumOfManifestationDTO> NumPerManifestation;

    public NumPerManifestationListDTO(List<NumOfManifestationDTO> numPerManifestation) {
        NumPerManifestation = numPerManifestation;
    }
    public NumPerManifestationListDTO(){}

    public List<NumOfManifestationDTO> getNumPerManifestation() {
        return NumPerManifestation;
    }

    public void setNumPerManifestation(List<NumOfManifestationDTO> numPerManifestation) {
        NumPerManifestation = numPerManifestation;
    }
}
