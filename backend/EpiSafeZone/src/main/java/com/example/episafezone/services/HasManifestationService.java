package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.repositories.HasManifestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HasManifestationService{
    @Autowired
    private HasManifestationRepository hasManifestationRepo;

    public List<HasManifestation> patientHasManifestations(Integer patientId) {
        return hasManifestationRepo.findByPatient(patientId);
    }

    public List<HasManifestation> findAll(){
        return hasManifestationRepo.findAll();
    }
}
