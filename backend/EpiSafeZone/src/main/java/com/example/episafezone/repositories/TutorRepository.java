package com.example.episafezone.repositories;

import com.example.episafezone.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    @Query("SELECT t.id FROM tutor t WHERE t.email = :email")
    Integer findIdByEmail(String email);

    Tutor findByEmail(String email);
}
