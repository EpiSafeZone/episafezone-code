package com.example.episafezone.repositories;

import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasManifestationRepository extends JpaRepository<HasManifestation, Integer> {
    List<HasManifestation> findByPatient(Integer patientId);
}
