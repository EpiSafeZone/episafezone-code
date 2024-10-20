package com.example.episafezone.services;

import com.example.episafezone.models.TutorOf;

import java.util.List;

public interface TutorOfServiceInterface {
    List<TutorOf> findAll();
    List<TutorOf> findByTutor(Integer tutorId);

    List<Integer> findPatientsByTutor(Integer tutorId);
}
