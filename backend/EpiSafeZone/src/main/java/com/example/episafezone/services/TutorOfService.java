package com.example.episafezone.services;

import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.TutorOfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorOfService {
    @Autowired
    TutorOfRepository tutorOfRepo;

    public List<TutorOf> findAll() {
        return tutorOfRepo.findAll();
    }

    public List<TutorOf> findByTutor(Integer tutorId){
        return tutorOfRepo.findByTutor(tutorId);
    }

    public List<Integer> findPatientsByTutor(Integer tutorId){
        List<TutorOf> tutorOfList = tutorOfRepo.findByTutor(tutorId);
        return tutorOfList.stream()
                .map(TutorOf::getPatient)
                .collect(Collectors.toList());
    }

}
