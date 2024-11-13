package com.example.episafezone.repositories;

import com.example.episafezone.models.Crisis;
import com.example.episafezone.models.HasManifestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrisisRespository extends JpaRepository<Crisis, Integer> {
    List<Crisis> findByPatient(Integer patientId);

}
