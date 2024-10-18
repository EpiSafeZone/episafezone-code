package com.example.episafezone.repositories;

import com.example.episafezone.models.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrisisRespository extends JpaRepository<Crisis, Integer> {
}
