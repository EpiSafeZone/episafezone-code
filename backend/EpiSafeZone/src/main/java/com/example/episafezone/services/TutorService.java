package com.example.episafezone.services;

import com.example.episafezone.models.Tutor;
import com.example.episafezone.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService{
    @Autowired
    TutorRepository tutorRepo;

    public List<Tutor> findAll() {
        return tutorRepo.findAll();
    }

    public Optional<Tutor> findById(int id) {
        return tutorRepo.findById(id);
    }
}