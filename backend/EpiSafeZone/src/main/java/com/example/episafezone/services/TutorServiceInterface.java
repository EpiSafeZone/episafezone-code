package com.example.episafezone.services;

import com.example.episafezone.models.Tutor;

import java.util.List;
import java.util.Optional;

public interface TutorServiceInterface {
    List<Tutor> findAll();
    Optional<Tutor> findById(int id);
}
