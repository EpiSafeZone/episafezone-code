package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;

import java.util.List;

public interface SharedWithServiceInterface {
    List<SharedWith> findAll();

    List<SharedWith> findByTutorReceiving(Integer tutorId);
}
