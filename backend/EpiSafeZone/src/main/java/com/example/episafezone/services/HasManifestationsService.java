package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.repositories.HasManifestationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HasManifestationsService implements HasManifestationsServiceInterface{
    private HasManifestationRepository hasManifestationRepo;


    public HasManifestationsService(HasManifestationRepository hasManifestationRepo) {
        this.hasManifestationRepo = hasManifestationRepo;
    }

    @Override
    public List<HasManifestation> thisPatientHas(Integer id) {
        List<HasManifestation> hasManifestationList = hasManifestationRepo.findAll();
        List<HasManifestation> res = new ArrayList<>();
        for (HasManifestation hasManifestation : hasManifestationList) {
            if(hasManifestation.getPatient().equals(id)) {
                res.add(hasManifestation);
            }
        }
        return res;
    }
}
