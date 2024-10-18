package com.example.episafezone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="manifestation")
public class Manifestation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "manifestation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crisis> crisis;

    public Manifestation(String name, String description) {
        this.name = name;
        this.description = description;
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
}
