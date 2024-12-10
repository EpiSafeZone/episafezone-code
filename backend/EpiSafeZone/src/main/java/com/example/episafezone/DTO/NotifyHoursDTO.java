package com.example.episafezone.DTO;

import java.io.Serializable;
import java.time.LocalTime;

public class NotifyHoursDTO implements Serializable {

    private Integer tutor;
    private Integer patient;
    private LocalTime notifyFrom;
    private LocalTime notifyTo;

    public NotifyHoursDTO(Integer tutor, Integer patient, LocalTime notifyFrom, LocalTime notifyTo) {
        this.tutor = tutor;
        this.patient = patient;
        this.notifyFrom = notifyFrom;
        this.notifyTo = notifyTo;
    }

    public NotifyHoursDTO(){}

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
