package com.example.episafezone.DTO;

import java.io.Serializable;

public class MedicationDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer dosis;
    private String unit;
    private Boolean alarm;

    public MedicationDTO(Integer id, String name, Integer dosis, String unit, Boolean alarm) {
        this.id = id;
        this.name = name;
        this.dosis = dosis;
        this.unit = unit;
        this.alarm = alarm;
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
