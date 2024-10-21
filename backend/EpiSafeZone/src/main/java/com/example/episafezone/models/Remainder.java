package com.example.episafezone.models;

import jakarta.persistence.*;

@Entity(name= "remainder")
public class Remainder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="time_of_remainder")
    private Integer timeOfRemainder;

    @Column(name="next_alarm")
    private Integer nextAlarm;

    private Integer medication;

    public Remainder(Integer timeOfRemainder, Integer nextAlarm, Medication medication) {
        this.timeOfRemainder = timeOfRemainder;
        this.nextAlarm = nextAlarm;
        this.medication = medication.getId();
    }

    public Remainder() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeOfRemainder() {
        return timeOfRemainder;
    }

    public void setTimeOfRemainder(Integer timeOfRemainder) {
        this.timeOfRemainder = timeOfRemainder;
    }

    public Integer getNextAlarm() {
        return nextAlarm;
    }

    public void setNextAlarm(Integer nextAlarm) {
        this.nextAlarm = nextAlarm;
    }

    public Integer getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication.getId();
    }
}
