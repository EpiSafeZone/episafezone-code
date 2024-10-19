package com.example.episafezone.repositories;

import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    List<Medication> findByPatientMedicated(Integer patientId);

}
