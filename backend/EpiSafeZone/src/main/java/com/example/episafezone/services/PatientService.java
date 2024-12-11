package com.example.episafezone.services;

import com.example.episafezone.DTO.CrisisDTO.CrisisDTO;
import com.example.episafezone.DTO.CrisisDTO.CrisisListDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationNameDTO;
import com.example.episafezone.DTO.ManifestationsDTO.NumOfManifestationDTO;
import com.example.episafezone.DTO.ManifestationsDTO.NumPerManifestationListDTO;
import com.example.episafezone.DTO.MedicationDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientCrisisDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientCrisisListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientListDTO;
import com.example.episafezone.DTO.SharedDTO.SharedTutorDTO;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.*;
import com.example.episafezone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    CrisisService crisisService;

    @Autowired
    TutorService tutorService;

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
    public PatientInfoDTO getPatientProfileInfo(Integer patientId, Integer userId) {
        Optional<Patient> patientOpt = patientRepo.findById(patientId);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            List<Medication> medications = medicationRepo.findByPatientMedicated(patientId);
            List<Manifestation> manifestations = manifestationService.getManifestationFromPatient(patientId);
            List<Tutor> sharedTutors = tutorService.findTutorsShared(patientId, userId);

            List<MedicationDTO> medicationDTOList = medications.stream()
                    .map(MedicationDTO::new)
                    .collect(Collectors.toList());

            List<ManifestationDTO> manifestationDTOList = manifestations.stream()
                    .map(ManifestationDTO::new)
                    .collect(Collectors.toList());

            List<SharedTutorDTO> sharedTutorDTOList = sharedTutors.stream()
                    .map(SharedTutorDTO::new)
                    .collect(Collectors.toList());

            return new PatientInfoDTO(
                    patient,
                    medicationDTOList,
                    manifestationDTOList,
                    sharedTutorDTOList

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
                            crisis.getPatient()C
                    );
                })
                .collect(Collectors.toList());

        CrisisListDTO CrisisListDTO = new CrisisListDTO(crisisDTOs);
        return CrisisListDTO;
    }
    public void editCounterOfMani(List<NumOfManifestationDTO> list, Integer maniId) {
        for (NumOfManifestationDTO dto : list) {
            // Buscar la manifestación cuyo ID coincida con maniId
            Integer id = manifestationService.getManifestationIdByName(dto.getName());
            if (id.equals(maniId)) {
                // Si encontramos la manifestación, incrementamos su contador
                dto.setNum(dto.getNum() + 1);
                break; // Detenemos el bucle porque ya actualizamos el contador
            }
        }
    }

    public Resource getImage(Integer patientId){
        return null;
    }

    public Boolean addImage(Integer patientId, MultipartFile file){

    }


    public Integer getNumOfApperances(Integer maniId, List<NumOfManifestationDTO> numPerManiList) {
        if (numPerManiList == null) {
            return -1;
        }
        return numPerManiList.stream()
                .filter(dto -> manifestationService.getManifestationIdByName(dto.getName()).equals(maniId))
                .findFirst()
                .map(dto -> dto.getNum())
                .orElse(-1);
    }


    public NumPerManifestationListDTO numPerManifestation(Integer patientId){
        List<Crisis> crisisFromPatient = crisisService.getByPatient(patientId);
        List<NumOfManifestationDTO> numPerManiList = new ArrayList<>();
        NumOfManifestationDTO dto;
        for (Integer i=crisisFromPatient.size()-1; i >= 0; i--){
            Integer maniId = crisisFromPatient.get(i).getManifestation();
            Integer count = getNumOfApperances(maniId,numPerManiList);
            if (count == -1) {
                // Crear un nuevo DTO solo si no existe
                dto = new NumOfManifestationDTO(
                        manifestationService.getManifestationNameById(maniId),
                        1
                );
                numPerManiList.add(dto);
            } else {
                // Actualizar el contador del DTO existente
                editCounterOfMani(numPerManiList, maniId);
            }

        }
        NumPerManifestationListDTO list = new NumPerManifestationListDTO(numPerManiList);
        return list;
    }

    public PatientCrisisListDTO lastWeekCrisis(Integer patientId){
        PatientCrisisListDTO list = new PatientCrisisListDTO(
                crisisService.lastWeekCrisis(patientId)
        );
        return list;
    }
}
