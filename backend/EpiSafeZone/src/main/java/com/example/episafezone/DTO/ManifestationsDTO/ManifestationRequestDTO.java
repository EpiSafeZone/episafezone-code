package com.example.episafezone.DTO.ManifestationsDTO;

import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;

public class ManifestationRequestDTO {
    private String name;
    private String description;
    private Integer patientId;

    public ManifestationRequestDTO(Manifestation manifestation, Patient patient) {
        this.name = manifestation.getName();
        this.description = manifestation.getDescription();
        this.patientId = manifestation.getId();
    }

    public ManifestationRequestDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
