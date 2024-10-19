package com.example.episafezone.services;

import com.example.episafezone.DTO.MedicationDTO;
import com.example.episafezone.DTO.PatientDTO;
import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.MedicationRepository;
import com.example.episafezone.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientServiceInteface{
    @Autowired
    PatientRepository patientRepo;

    @Autowired
    MedicationRepository medicationRepo;


    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Optional<Patient> findById(int id) {
        return patientRepo.findById(id);
    }

    @Override
    public PatientDTO getPatientProfileInfo(Integer patientId) {
        Optional<Patient> patientOpt = patientRepo.findById(patientId);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            List<Medication> medications = medicationRepo.findByPatientMedicated(patientId);

            List<MedicationDTO> medicationDTOList = medications.stream()
                    .map(medication -> new MedicationDTO(
                            medication.getId(),
                            medication.getName(),
                            medication.getDosis(),
                            medication.getUnit(),
                            medication.getAlarm()))
                    .collect(Collectors.toList());

            return new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getSurname(),
                    patient.getHeight(),
                    patient.getWeight(),
                    patient.getBirthdate(),
                    patient.getAge(),
                    patient.getColor(),
                    medicationDTOList
            );
        }else {
            throw new RuntimeException("Patient not found with id: " + patientId);
        }
    }
}
