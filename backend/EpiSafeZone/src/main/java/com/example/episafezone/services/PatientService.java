package com.example.episafezone.services;

import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements PatientServiceInteface{
    @Autowired
    PatientRepository patientRepo;


    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Optional<Patient> findById(int id) {
        return patientRepo.findById(id);
    }
}
