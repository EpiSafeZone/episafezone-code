package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.HasManifestationRepository;
import com.example.episafezone.repositories.ManifestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManifestationService implements ManifestationServiceInterface{
    @Autowired
    ManifestationRepository manifestationRepo;

    @Autowired
    PatientService patientService;

    @Autowired
    HasManifestationsService hasManifestationsService;

    @Override
    public List<Manifestation> getDefaultManifestation() {
        return List.of(this.getManifestationById(1), this.getManifestationById(2));
    }

    @Override
    public Manifestation getManifestationById(Integer id) {
        Optional<Manifestation> manifestation =  manifestationRepo.findById(id);
        if(manifestation.isPresent()){
            return manifestation.get();
        }else{
            throw new ResourceNotFoudException("No se ha encontrado la manifestation con el id: " + id);
        }
    }

    @Override
    public List<Manifestation> getManifestationFromPatient(Integer id) {
        Patient patient = patientService.findById(id);
        List<HasManifestation> hasManifestationList = hasManifestationsService.thisPatientHas(patient.getId());
        List<Manifestation> manifestationList = new ArrayList<>();
        for (HasManifestation hasManifestation : hasManifestationList) {
            manifestationList.add(getManifestationById(hasManifestation.getManifestation()));
        }
        return manifestationList;
    }

    @Override
    public Manifestation create(Manifestation manifestation) {
        return manifestationRepo.save(manifestation);
    }

    @Override
    public Manifestation update(Integer id, Manifestation manifestation) {
        Manifestation toUpdate = getManifestationById(id);
        toUpdate.setName(manifestation.getName());
        toUpdate.setDescription(manifestation.getDescription());
        return manifestationRepo.save(toUpdate);
    }
}
