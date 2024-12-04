package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.SharedWithService;
import com.example.episafezone.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService implements TutorServiceInterface{
    @Autowired
    TutorRepository tutorRepo;

    @Autowired
    SharedWithService sharedWithService;

    @Override
    public List<Tutor> findAll() {
        return tutorRepo.findAll();
    }

    @Override
    public Tutor findById(int id) {
        if(tutorRepo.findById(id).isPresent()){
            return tutorRepo.findById(id).get();
        }else{
            throw new ResourceNotFoudException("No se ha encontrdo un tutor para el id: " + id);
        }
    }

    public List<Tutor> findTutorsShared(Integer tutotSId, Integer patient){
        List<SharedWith> sharedWithList = sharedWithService.findByTutorSAndPatient(tutotSId, patient);
        List <Integer> tutorSharedIds = sharedWithList.stream()
                .map(SharedWith::getTutorReceiving)
                .toList();
        List<Tutor> tutorsShared = tutorSharedIds.stream()
                .map(tutorId -> tutorRepo.findById(tutorId).orElse(null))
                .toList();
        return tutorsShared;
    }
}