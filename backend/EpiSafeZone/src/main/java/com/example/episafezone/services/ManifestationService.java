package com.example.episafezone.services;

import com.example.episafezone.DTO.ManifestationsDTO.ManifestationDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationNameDTO;
import com.example.episafezone.DTO.ManifestationsDTO.ManifestationRequestDTO;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.HasManifestationRepository;
import com.example.episafezone.repositories.ManifestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManifestationService implements ManifestationServiceInterface {
    @Autowired
    ManifestationRepository manifestationRepo;

    @Autowired
    HasManifestationService hasManifestationService;

    @Autowired
    PatientService patientService;

    @Autowired
    private HasManifestationRepository hasManifestationRepository;

    @Override
    public List<Manifestation> getDefaultManifestation() {
        return List.of(getManifestationById(1), getManifestationById(2));
    }

    @Override
    public Manifestation getManifestationById(Integer id) {
        Optional<Manifestation> manifestation = manifestationRepo.findById(id);
        if (manifestation.isPresent()) {
            return manifestation.get();
        } else {
            throw new ResourceNotFoudException("No se ha encontrado el manifestation con el id: " + id);
        }
    }

    public Manifestation getManifestationByName(String name) {
        return manifestationRepo.findByName(name);

    }

    public String getManifestationNameById(Integer id){
        return getManifestationById(id).getName();
    }

    public Integer getManifestationIdByName(String name){
        return getManifestationByName(name).getId();
    }

    @Override
    public List<Manifestation> getManifestationFromPatient(Integer patientId) {
        List<HasManifestation> hasManif = hasManifestationService.patientHasManifestations(patientId);

        List<Integer> manifestationIds = hasManif.stream()
                .map(HasManifestation::getManifestation)
                .collect(Collectors.toList());

        List<Manifestation> manifestations = manifestationIds.stream()
                .map(this::getManifestationById)
                .collect(Collectors.toList());

        return manifestations;
    }


    public Manifestation create(ManifestationRequestDTO manifestationRequestDTO) {
        Manifestation manifestation = new Manifestation(manifestationRequestDTO.getName(), manifestationRequestDTO.getDescription(), manifestationRequestDTO.getSteps());
        manifestationRepo.save(manifestation);
        Patient patient = patientService.findById(manifestationRequestDTO.getPatientId());
        HasManifestation hasManifestation = new HasManifestation(manifestation, patient);
        hasManifestationRepository.save(hasManifestation);
        return manifestation;
    }

    public Manifestation update(Integer id, Manifestation manifestation) {
        Manifestation toUpdate = getManifestationById(id);
        toUpdate.setName(manifestation.getName());
        toUpdate.setDescription(manifestation.getDescription());
        toUpdate.setSteps(manifestation.getSteps());
        return toUpdate;
    }

    public void delete(Integer id) {
        getManifestationById(id);
        manifestationRepo.deleteById(id);
    }
}
