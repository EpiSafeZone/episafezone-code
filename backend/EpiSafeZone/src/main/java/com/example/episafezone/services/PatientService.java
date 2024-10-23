package com.example.episafezone.services;

import com.example.episafezone.DTO.ManifestationDTO;
import com.example.episafezone.DTO.MedicationDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientListDTO;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.*;
import com.example.episafezone.services.SharedWithService;
import com.example.episafezone.services.TutorOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientServiceInteface {
    @Autowired
    PatientRepository patientRepo;

    @Autowired
    MedicationRepository medicationRepo;

    @Autowired
    ManifestationService manifestationService;

    @Autowired
    HasManifestationService hasManifestationService;

    @Autowired
    TutorOfService tutorOfService;

    @Autowired
    SharedWithService sharedWithService;


    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Optional<Patient> findById(int id) {
        return patientRepo.findById(id);
    }

    @Override
    public PatientInfoDTO getPatientProfileInfo(Integer patientId) {
        Optional<Patient> patientOpt = patientRepo.findById(patientId);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            List<Medication> medications = medicationRepo.findByPatientMedicated(patientId);
            List<Manifestation> manifestations = manifestationService.getManifestationFromPatient(patientId);

            List<MedicationDTO> medicationDTOList = medications.stream()
                    .map(MedicationDTO::new)
                    .collect(Collectors.toList());

            List<ManifestationDTO> manifestationDTOList = manifestations.stream()
                    .map(ManifestationDTO::new)
                    .collect(Collectors.toList());

            return new PatientInfoDTO(
                    patient,
                    medicationDTOList,
                    manifestationDTOList

            );
        } else {
            throw new RuntimeException("Patient not found with id: " + patientId);
        }
    }

    public List<HasManifestation> getPatientManifestations(Integer id) {
        Optional<Patient> patientOpt = patientRepo.findById(id);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            List<HasManifestation> listManifestation = hasManifestationService.findAll();
            listManifestation = listManifestation.stream().filter(hasManifestation ->
                    hasManifestation.getPatient().equals(patient.getId())
            ).collect(Collectors.toList());

            return listManifestation;

        } else {
            return null;
        }
    }

    public List<PatientListDTO> getPatientList(Integer tutorId) {
        List<Integer> patients = tutorOfService.findPatientsByTutor(tutorId);
        List<Integer> patientsShared = sharedWithService.findPByTReceiving(tutorId);

        patients.addAll(patientsShared);

        List<PatientListDTO> patientListDTOs = patients.stream()
                .map(patientId -> {
                    Optional<Patient> patientOpt = patientRepo.findById(patientId);
                    if (patientOpt.isPresent()) {
                        Patient patient = patientOpt.get();
                        return new PatientListDTO(patient);
                    } else {
                        throw new RuntimeException("Patient not found with ID: " + patientId);
                    }
                })
                .collect(Collectors.toList());

        return patientListDTOs;
    }
}
