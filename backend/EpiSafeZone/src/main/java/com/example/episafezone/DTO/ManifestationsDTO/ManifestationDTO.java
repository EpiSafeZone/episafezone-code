package com.example.episafezone.DTO.ManifestationsDTO;

import com.example.episafezone.models.Manifestation;

import java.io.Serializable;

public class ManifestationDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String steps;

    public ManifestationDTO(Manifestation manifestation) {
        this.id = manifestation.getId();
        this.name = manifestation.getName();
        this.description = manifestation.getDescription();
        this.steps = manifestation.getSteps();
    }

    public ManifestationDTO(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
