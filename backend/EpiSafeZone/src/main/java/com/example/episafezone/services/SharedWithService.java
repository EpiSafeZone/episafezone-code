package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.repositories.SharedWithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedWithService implements SharedWithServiceInterface {
    @Autowired
    SharedWithRepository sharedWithRepo;

    @Override
    public List<SharedWith> findAll() {
        return sharedWithRepo.findAll();
    }

    @Override
    public List<SharedWith> findByTutorReceiving(Integer tutorId) {
        return sharedWithRepo.findByTutorReceiving(tutorId);
    }

}
