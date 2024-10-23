package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HasManifestationServiceInterface {
    public List<HasManifestation> patientHasManifestations(Integer patientId);
}
