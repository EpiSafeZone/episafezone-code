package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.TutorOfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorOfService implements TutorOfServiceInterface{
    @Autowired
    TutorOfRepository tutorOfRepo;

    @Override
    public List<TutorOf> findAll() {
        return tutorOfRepo.findAll();
    }

    @Override
    public List<TutorOf> findByTutor(Integer tutorId){
        return tutorOfRepo.findByTutor(tutorId);
    }

    @Override
    public List<Integer> findPatientsByTutor(Integer tutorId){
        List<TutorOf> tutorOfList = tutorOfRepo.findByTutor(tutorId);
        return tutorOfList.stream()
                .map(TutorOf::getPatient)
                .collect(Collectors.toList());
    }

    public TutorOf addTutorOf(
            Integer tutor,
            Integer patient,
            Boolean master) {

        TutorOf tutorOf = new TutorOf(
                tutor,
                patient,
                master
        );
        return tutorOfRepo.save(tutorOf);
    }

    public TutorOf deleteTutorOf(Integer tutor, Integer patient){
        TutorOf tutorOf = tutorOfRepo.findByTutorAndPatient(tutor,patient);
        tutorOfRepo.delete(tutorOf);
        return tutorOf;
    }

    public TutorOf findByTutorAndPatient(Integer tutor, Integer patient){
        return tutorOfRepo.findByTutorAndPatient(tutor, patient);
    }
}
