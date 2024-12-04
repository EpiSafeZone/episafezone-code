package com.example.episafezone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "shared_with")
public class SharedWith {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tutorSharing;
    private Integer tutorReceiving;
    private Integer patient;
    private Boolean registerCrisisPermision;
    private Boolean profilePermision;
    private Boolean medicinePermision;
    private Boolean tutorPermision;

    public SharedWith(Integer tutorSharing, Integer tutorReceiving, Integer patient, Boolean registerCrisisPermision,
                      Boolean profilePermision, Boolean medicinePermision, Boolean tutorPermision) {
        this.tutorSharing = tutorSharing;
        this.tutorReceiving = tutorReceiving;
        this.patient = patient;
        this.registerCrisisPermision = registerCrisisPermision;
        this.profilePermision = profilePermision;
        this.medicinePermision = medicinePermision;
        this.tutorPermision = tutorPermision;
    }

    public SharedWith(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTutorSharing() {
        return tutorSharing;
    }

    public void setTutorSharing(Integer tutorSharing) {
        this.tutorSharing = tutorSharing;
    }

    public Integer getTutorReceiving() {
        return tutorReceiving;
    }

    public void setTutorReceiving(Integer tutorReceiving) {
        this.tutorReceiving = tutorReceiving;
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
