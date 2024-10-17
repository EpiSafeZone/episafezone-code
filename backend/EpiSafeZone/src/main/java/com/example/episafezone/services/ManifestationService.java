package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.ManifestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManifestationService implements ManifestationServiceInterface{
    @Autowired
    ManifestationRepository manifestationRepo;

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
    public List<Manifestation> getManifestationFromPatient(Patient patient) {
        return List.of();
    }
}
