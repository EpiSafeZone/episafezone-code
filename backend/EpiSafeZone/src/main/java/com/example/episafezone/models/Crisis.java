package com.example.episafezone.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity(name="crisis")
public class Crisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer duration;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String hour;
    private String context;
    private Boolean emergency;
    private Integer manifestation;
    private Integer patient;


    public Crisis(Integer duration, Date date, String hour, String context, Boolean emergency, Manifestation manifestation, Patient patient) {
        this.duration = duration;
        this.date = date;
        this.hour = hour;
        this.context = context;
        this.emergency = emergency;
        this.manifestation = manifestation.getId();
        this.patient = patient.getId();
    }

    public Crisis() {
    }

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

    public Integer getManifestation() {
        return manifestation;
    }

    public void setManifestation(Integer manifestation) {
        this.manifestation = manifestation;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public String toString(){
        return "duración" + this.hour + "con contexto" + this.context;
    }
}
