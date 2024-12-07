package com.example.episafezone.repositories;

import com.example.episafezone.models.NotifyHours;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyHoursRepository extends JpaRepository<NotifyHours, Integer> {
    NotifyHours findByPatientAndTutor(Integer patient, Integer tutor);
}
