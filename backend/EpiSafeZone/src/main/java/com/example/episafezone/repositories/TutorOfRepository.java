package com.example.episafezone.repositories;

import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorOfRepository extends JpaRepository<TutorOf, Integer> {
    List<TutorOf> findByTutor(Integer tutorId);
}
