package com.example.episafezone.services;

import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;

import java.util.List;
import java.util.Optional;

public interface PatientServiceInteface {
    List<Patient> findAll();
    Optional<Patient> findById(int id);

}
