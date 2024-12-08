package com.example.episafezone.DTO.SharedDTO;

import java.io.Serializable;

public class SharedPermissionsDTO implements Serializable {

    private Integer tutorReciving;
    private Integer patient;
    private Boolean registerCrisisPermision;
    private Boolean profilePermision;
    private Boolean medicinePermision;
    private Boolean tutorPermision;

    public SharedPermissionsDTO(Integer tutorReciving, Integer patient, Boolean registerCrisisPermision, Boolean profilePermision, Boolean medicinePermision, Boolean tutorPermision) {
        this.tutorReciving = tutorReciving;
        this.patient = patient;
        this.registerCrisisPermision = registerCrisisPermision;
        this.profilePermision = profilePermision;
        this.medicinePermision = medicinePermision;
        this.tutorPermision = tutorPermision;
    }

    public SharedPermissionsDTO(){}

    public Integer getTutorReciving() {
        return tutorReciving;
    }

    public void setTutorReciving(Integer tutorReciving) {
        this.tutorReciving = tutorReciving;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public Boolean getRegisterCrisisPermision() {
        return registerCrisisPermision;
    }

    public void setRegisterCrisisPermision(Boolean registerCrisisPermision) {
        this.registerCrisisPermision = registerCrisisPermision;
    }

    public Boolean getProfilePermision() {
        return profilePermision;
    }

    public void setProfilePermision(Boolean profilePermision) {
        this.profilePermision = profilePermision;
    }

    public Boolean getMedicinePermision() {
        return medicinePermision;
    }

    public void setMedicinePermision(Boolean medicinePermision) {
        this.medicinePermision = medicinePermision;
    }

    public Boolean getTutorPermision() {
        return tutorPermision;
    }

    public void setTutorPermision(Boolean tutorPermision) {
        this.tutorPermision = tutorPermision;
    }
}
