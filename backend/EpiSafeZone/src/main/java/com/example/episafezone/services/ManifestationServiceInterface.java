package com.example.episafezone.services;

import com.example.episafezone.models.Manifestation;
import java.util.List;

public interface ManifestationServiceInterface {
    List<Manifestation> getDefaultManifestation();
    Manifestation getManifestationById(Integer id);
    List<Manifestation> getManifestationFromPatient(Integer patientId);
}

