package com.example.episafezone.repositories;

import com.example.episafezone.models.Manifestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifestationRepository extends JpaRepository<Manifestation, Integer> {
    Manifestation findByName(String name);
}
