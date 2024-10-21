package com.example.episafezone.models;


import jakarta.persistence.*;

import java.util.List;

@Entity(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer dosis;
    private String unit;
    private Boolean alarm;
    private Integer patientMedicated;


    public Medication(Integer id, String name, Integer dosis, String unit, Boolean alarm, Integer patientMedicated) {
        this.id = id;
        this.name = name;
        this.dosis = dosis;
        this.unit = unit;
        this.alarm = alarm;
        this.patientMedicated = patientMedicated;
    }

    public Medication(){}

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

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public Integer getPatientMedicated() {
        return patientMedicated;
    }

    public void setPatientMedicated(Integer patientMedicated) {
        this.patientMedicated = patientMedicated;
    }
}
