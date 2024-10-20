package com.example.episafezone.services;

import com.example.episafezone.DTO.PatientDTO;
import com.example.episafezone.models.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientServiceInteface {
    List<Patient> findAll();
    Optional<Patient> findById(int id);

    PatientDTO getPatientProfileInfo(Integer patientId);
}
