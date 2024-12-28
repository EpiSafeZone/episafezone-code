package com.example.episafezone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="manifestation")
public class Manifestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String steps;

    public Manifestation(String name, String description, String steps) {
        this.name = name;
        this.description = description;
        this.steps = steps;
    }
    public Manifestation() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
