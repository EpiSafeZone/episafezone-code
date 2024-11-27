package com.example.episafezone.services;

import com.example.episafezone.models.Tutor;

import java.util.List;

public interface TutorServiceInterface {
    List<Tutor> findAll();
    Tutor findById(int id);
}
