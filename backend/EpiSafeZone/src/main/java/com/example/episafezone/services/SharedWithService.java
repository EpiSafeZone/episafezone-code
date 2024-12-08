package com.example.episafezone.services;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.SharedWithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharedWithService implements SharedWithServiceInterface {
    @Autowired
    SharedWithRepository sharedWithRepo;

    @Autowired
    TutorOfService tutorOfService;

    public static TutorService tutorService;

    @Override
    public List<SharedWith> findAll() {
        return sharedWithRepo.findAll();
    }

    @Override
    public List<SharedWith> findByTutorReceiving(Integer tutorId) {
        return sharedWithRepo.findByTutorReceiving(tutorId);
    }
    @Override
    public List<Integer> findPByTReceiving(Integer tutorId){
        List<SharedWith> tutorOfList = sharedWithRepo.findByTutorReceiving(tutorId);
        return tutorOfList.stream()
                .map(SharedWith::getPatient)
                .collect(Collectors.toList());
    }

     public List<Tutor> findPatientTutors(Integer patientId){
        List<Tutor> tutors = new ArrayList<Tutor>();
        for(SharedWith sharedWith : sharedWithRepo.findAll()){
            if(sharedWith.getPatient().equals(patientId)){
                Tutor tutor = tutorService.findById(sharedWith.getTutorReceiving());
                tutors.add(tutor);
            }
        }
        for(TutorOf tutorOf: tutorOfService.findAll()){
            if(tutorOf.getPatient().equals(patientId)){
                Tutor tutor = tutorService.findById(tutorOf.getTutor());
                tutors.add(tutor);
            }
        }
        return tutors;
     }

    public List<SharedWith> findByTutorSharing(Integer tutorId) {
        return sharedWithRepo.findByTutorSharing(tutorId);
    }

    public List<SharedWith> findByTutorShAndPatient(Integer tutorShId, Integer patient){
        List<SharedWith> filteredByTutorSh = findByTutorSharing(tutorShId);
        return filteredByTutorSh.stream()
                .filter(sharedWith -> sharedWith.getPatient().equals(patient))
                .toList();
    }

    public SharedWith sharePatient(
            Integer tutorSharingId,
            Integer tutorReceivingId,
            Integer patientId,
            Boolean registerCrisisPermission,
            Boolean profilePermission,
            Boolean medicinePermission,
            Boolean tutorPermission) {

        SharedWith sharedWith = new SharedWith(
                tutorSharingId,
                tutorReceivingId,
                patientId,
                registerCrisisPermission,
                profilePermission,
                medicinePermission,
                tutorPermission
        );

        return sharedWithRepo.save(sharedWith);
    }
}
