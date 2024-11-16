package com.example.episafezone.DTO.PatientsDTO;

import com.example.episafezone.DTO.ManifestationsDTO.ManifestationDTO;
import com.example.episafezone.DTO.MedicationDTO;
import com.example.episafezone.models.Patient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientInfoDTO implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private Integer height;
    private Integer weight;
    private Date birthdate;
    private Integer age;
    private String color;

    private List<MedicationDTO> medications;
    private List<ManifestationDTO> manifestations;


    public PatientInfoDTO(Patient patient, List<MedicationDTO> medications, List<ManifestationDTO> manifestations) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.birthdate = patient.getBirthdate();
        this.age = patient.getAge();
        this.color = patient.getColor();
        this.medications = medications;
        this.manifestations = manifestations;
    }

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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }

    public List<ManifestationDTO> getManifestations() {
        return manifestations;
    }

    public void setManifestations(List<ManifestationDTO> manifestations) {
        this.manifestations = manifestations;
    }
}
