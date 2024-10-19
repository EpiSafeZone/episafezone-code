package com.example.episafezone.services;

import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.MedicationRepository;
import com.example.episafezone.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService implements MedicationServiceInterface{
    @Autowired
    MedicationRepository medicationRepo;

    @Autowired
    PatientRepository patientRepo;


    @Override
    public List<Medication> findAll() {
        return medicationRepo.findAll();
    }

    @Override
    public Optional<Medication> findById(int id) {
        return medicationRepo.findById(id);
    }

    @Override
    public List<Medication> findMedicationsByPatient(Integer patientId) {
        return medicationRepo.findByPatientMedicated(patientId);
    }

}
