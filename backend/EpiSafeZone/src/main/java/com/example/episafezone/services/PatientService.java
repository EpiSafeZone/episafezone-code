package com.example.episafezone.services;

import com.example.episafezone.DTO.CrisisDTO.CrisisDTO;
import com.example.episafezone.DTO.CrisisDTO.CrisisListDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationNameDTO;
import com.example.episafezone.DTO.MedicationDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientListDTO;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.*;
import com.example.episafezone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Lazy
    @Autowired
    ManifestationService manifestationService;

    @Autowired
    HasManifestationService hasManifestationService;

    @Autowired
    TutorOfService tutorOfService;

    @Autowired
    SharedWithService sharedWithService;

    @Autowired
    CrisisService CrisisService;


    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Patient findById(int id) {
        if(patientRepo.findById(id).isPresent()){
            return patientRepo.findById(id).get();
        }else{
            throw new ResourceNotFoudException("No se ha encontrado el paciente con el id: " + id);
        }
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

    public CrisisListDTO getListOfCrisis(Integer id, Integer year, Integer month){
        List<Crisis> unfilteredList = CrisisService.getByPatient(id);
        List<Crisis> filteredList = CrisisService.getByMonth(unfilteredList, year, month);

        List<CrisisDTO> crisisDTOs= filteredList.stream()
                .map(crisis -> {
                    Manifestation manifestation = manifestationService.getManifestationById(crisis.getManifestation());
                    ManifestationNameDTO manifestationNameDTO = manifestation != null ? new ManifestationNameDTO(manifestation) : null;

                    return new CrisisDTO(
                            crisis.getId(),
                            crisis.getDuration(),
                            crisis.getDate(),
                            crisis.getHour(),
                            crisis.getContext(),
                            crisis.getEmergency(),
                            manifestationNameDTO,
                            crisis.getPatient()
                    );
                })
                .collect(Collectors.toList());

        CrisisListDTO CrisisListDTO = new CrisisListDTO(crisisDTOs);
        return CrisisListDTO;
    }


}
