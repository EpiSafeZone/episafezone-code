package com.example.episafezone.services;

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
public class ManifestationService implements ManifestationServiceInterface{
    @Autowired
    ManifestationRepository manifestationRepo;

    @Autowired
    HasManifestationService hasManifestationService;

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
            throw new ResourceNotFoudException("No se ha encontrado el manifestation con el id: " + id);
        }
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

    public Manifestation create(Manifestation manifestation) {
        return manifestationRepo.save(manifestation);
    }

    public Manifestation update(Integer id, Manifestation manifestation) {
        Manifestation toUpdate = getManifestationById(id);
        toUpdate.setName(manifestation.getName());
        toUpdate.setDescription(manifestation.getDescription());
        return manifestationRepo.save(toUpdate);
    }

    public void delete(Integer id) {
        getManifestationById(id);
        manifestationRepo.deleteById(id);
    }
}
