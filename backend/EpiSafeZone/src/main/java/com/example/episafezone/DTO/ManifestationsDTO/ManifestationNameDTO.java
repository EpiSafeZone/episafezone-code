package com.example.episafezone.DTO.ManifestationsDTO;

import com.example.episafezone.models.Manifestation;

import java.io.Serializable;

public class ManifestationNameDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;

    public ManifestationNameDTO(Manifestation manifestation) {
        this.name = manifestation.getName();
    }

    public ManifestationNameDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
