package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;

import java.util.List;

public interface HasManifestationsServiceInterface {
    public List<HasManifestation> thisPatientHas(Integer id);
}
