package com.example.episafezone.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity(name="crisis")
public class Crisis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer duration;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String hour;
    private String context;
    private Boolean emergency;
    @ManyToOne
    @JoinColumn(name="manifestation", referencedColumnName = "id", nullable = false)
    private Manifestation manifestation;
    @ManyToOne
    @JoinColumn(name="patient", referencedColumnName = "id", nullable = false)
    private Patient patient;


    public Crisis(Integer duration, Date date, String hour, String context, Boolean emergency, Manifestation manifestation, Patient patient) {
        this.duration = duration;
        this.date = date;
        this.hour = hour;
        this.context = context;
        this.emergency = emergency;
        this.manifestation = manifestation;
        this.patient = patient;
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

    public Manifestation getManifestation() {
        return manifestation;
    }

    public void setManifestation(Manifestation manifestation) {
        this.manifestation = manifestation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String toString(){
        return "duración" + this.hour + "con contexto" + this.context;
    }
}
