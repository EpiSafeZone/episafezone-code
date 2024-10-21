package com.example.episafezone.repositories;

import com.example.episafezone.models.HasManifestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasManifestationRepository extends JpaRepository<HasManifestation, Integer> {
}
