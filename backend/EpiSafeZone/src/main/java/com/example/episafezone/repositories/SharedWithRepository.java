package com.example.episafezone.repositories;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.TutorOf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharedWithRepository extends JpaRepository<SharedWith, Integer> {
    List<SharedWith> findByTutorReceiving(Integer tutorId);
    List<SharedWith> findByTutorSharing(Integer tutorId);
    List<SharedWith> findByPatient(Integer tutorId);
}
