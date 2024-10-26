package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.repositories.SharedWithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharedWithService {
    @Autowired
    SharedWithRepository sharedWithRepo;

    public List<SharedWith> findAll() {
        return sharedWithRepo.findAll();
    }

    public List<SharedWith> findByTutorReceiving(Integer tutorId) {
        return sharedWithRepo.findByTutorReceiving(tutorId);
    }

    public List<Integer> findPByTReceiving(Integer tutorId){
        List<SharedWith> tutorOfList = sharedWithRepo.findByTutorReceiving(tutorId);
        return tutorOfList.stream()
                .map(SharedWith::getPatient)
                .collect(Collectors.toList());
    }
}
