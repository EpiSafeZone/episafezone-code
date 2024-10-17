package com.example.episafezone.services;

import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;

import java.util.List;
import java.util.Optional;

public interface ManifestationServiceInterface {
    List<Manifestation> getDefaultManifestation();
    Manifestation getManifestationById(Integer id);
    List<Manifestation> getManifestationFromPatient(Patient patient);
}

