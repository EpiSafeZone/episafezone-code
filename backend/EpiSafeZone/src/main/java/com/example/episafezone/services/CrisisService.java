package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Crisis;
import com.example.episafezone.repositories.CrisisRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrisisService implements CrisisServiceInterface {
    private final CrisisRespository repo;

    public CrisisService(CrisisRespository repo) {
        this.repo = repo;
    }

    public List<Crisis> getAll(){
        return repo.findAll();
    }

    public Crisis getById(Integer id){
        Optional<Crisis> crisisOptional = repo.findById(id);
        if(crisisOptional.isPresent()){
            return crisisOptional.get();
        }else{
            throw new ResourceNotFoudException("No se ha encontrado la crisis asociada a este id" + id);
        }
    }
}
