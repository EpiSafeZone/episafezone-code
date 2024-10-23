package com.example.episafezone.services;

import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicationServiceInterface {
    List<Medication> findAll();
    Medication findById(int id);
    List<Medication> findMedicationsByPatient(Integer patientId);

}
