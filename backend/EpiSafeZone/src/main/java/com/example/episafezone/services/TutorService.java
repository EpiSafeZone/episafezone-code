package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService implements TutorServiceInterface{
    @Autowired
    TutorRepository tutorRepo;

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
}