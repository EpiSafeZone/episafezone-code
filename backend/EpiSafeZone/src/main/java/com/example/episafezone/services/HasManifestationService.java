package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.repositories.HasManifestationRepository;
import com.example.episafezone.repositories.ManifestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HasManifestationService implements HasManifestationServiceInterface {
    @Autowired
    private HasManifestationRepository hasManifestationRepo;

    @Override
    public List<HasManifestation> patientHasManifestations(Integer patientId) {
        List<HasManifestation> res = new ArrayList<>();
        List<HasManifestation> hasManifestationList = hasManifestationRepo.findAll();
        for (HasManifestation hasManifestation : hasManifestationList) {
            if(hasManifestation.getPatient().equals(patientId)){
                res.add(hasManifestation);
            }
        }
        return res;
    }
}
