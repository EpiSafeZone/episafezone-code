package com.example.episafezone.DTO;

import com.example.episafezone.models.Medication;

import java.io.Serializable;

public class MedicationDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer dosis;
    private String unit;
    private Boolean alarm;

    public MedicationDTO(Medication medication) {
        this.id = medication.getId();
        this.name = medication.getName();
        this.dosis = medication.getDosis();
        this.unit = medication.getUnit();
        this.alarm = medication.getAlarm();
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
}
