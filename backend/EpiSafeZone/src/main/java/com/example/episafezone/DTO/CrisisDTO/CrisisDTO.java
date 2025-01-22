package com.example.episafezone.DTO.CrisisDTO;

import com.example.episafezone.DTO.ManifestationsDTO.ManifestationNameDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

public class CrisisDTO implements Serializable {
    private Integer id;
    private Integer duration;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String hour;
    private String context;
    private Boolean emergency;
    private String manifestationName;
    private Integer patient;

    public CrisisDTO(Integer id, Integer duration, Date date, String hour, String context, Boolean emergency, String manifestationName, Integer patient) {
        this.id = id;
        this.duration = duration;
        this.date = date;
        this.hour = hour;
        this.context = context;
        this.emergency = emergency;
        this.manifestationName = manifestationName;
        this.patient = patient;
    }

    public CrisisDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getManifestation() {
        return manifestationName;
    }

    public void setManifestation(String manifestation) {
        this.manifestationName = manifestation;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
