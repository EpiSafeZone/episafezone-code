package com.example.episafezone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity(name = "notify_hours")
public class NotifyHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tutor;
    private Integer patient;
    private LocalTime notifyFrom;
    private LocalTime notifyTo;

    public NotifyHours(Integer id, Integer tutor, Integer patient, LocalTime notifyFrom, LocalTime notifyTo) {
        this.id = id;
        this.tutor = tutor;
        this.patient = patient;
        this.notifyFrom = notifyFrom;
        this.notifyTo = notifyTo;
    }

    public NotifyHours(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTutor() {
        return tutor;
    }

    public void setTutor(Integer tutor) {
        this.tutor = tutor;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

    public LocalTime getNotifyFrom() {
        return notifyFrom;
    }

    public void setNotifyFrom(LocalTime notifyFrom) {
        this.notifyFrom = notifyFrom;
    }

    public LocalTime getNotifyTo() {
        return notifyTo;
    }

    public void setNotifyTo(LocalTime notifyTo) {
        this.notifyTo = notifyTo;
    }
}
