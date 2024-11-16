package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import java.util.List;

public interface HasManifestationServiceInterface {
    public List<HasManifestation> patientHasManifestations(Integer patientId);
}
