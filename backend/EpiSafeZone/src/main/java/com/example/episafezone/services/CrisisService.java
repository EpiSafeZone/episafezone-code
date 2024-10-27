package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Crisis;
import com.example.episafezone.repositories.CrisisRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrisisService {
    @Autowired
    private CrisisRespository repo;

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

    public List<Crisis> getByPatient(Integer id){
        return repo.findByPatient(id);
    }

    public List<Crisis> getByMonth(List<Crisis> crisisList, int year, int month) {
        List<Crisis> filteredList = new ArrayList<>();

        for (Crisis crisis : crisisList) {
            Date date = crisis.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int crisisYear = calendar.get(Calendar.YEAR);
            int crisisMonth = calendar.get(Calendar.MONTH) + 1;

            if (crisisYear == year && crisisMonth == month) {
                filteredList.add(crisis);
            }
        }
        filteredList.sort(Comparator.comparing(Crisis::getDate));
        return filteredList;
    }

    public Crisis update(Integer id, Crisis crisis) {
        Crisis toUpdate = getById(id);
        toUpdate.setDuration(crisis.getDuration());
        toUpdate.setDate(crisis.getDate());
        toUpdate.setHour(crisis.getHour());
        toUpdate.setContext(crisis.getContext());
        toUpdate.setContext(crisis.getContext());
        toUpdate.setManifestation(crisis.getManifestation());
        toUpdate.setPatient(crisis.getPatient());
        return repo.save(toUpdate);

    }

    public void delete (Integer id) {
        Crisis toDelete = getById(id);
        repo.delete(toDelete);
    }
}
