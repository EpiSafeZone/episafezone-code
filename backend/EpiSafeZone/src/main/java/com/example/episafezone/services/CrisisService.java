package com.example.episafezone.services;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.CrisisEvent;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Crisis;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.CrisisRespository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrisisService implements CrisisServiceInterface {
    private final CrisisRespository repo;

    public CrisisService(CrisisRespository repo) {
        this.repo = repo;
    }

    public List<Crisis> getAll() {
        return repo.findAll();
    }

    public Crisis getById(Integer id) {
        Optional<Crisis> crisisOptional = repo.findById(id);
        if (crisisOptional.isPresent()) {
            return crisisOptional.get();
        } else {
            throw new ResourceNotFoudException("No se ha encontrado la crisis asociada a este id" + id);
        }
    }

    public List<Crisis> getByPatient(Integer id) {
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

    public Crisis createCrisis(Crisis crisis) {
        PatientService patientService = SpringContext.getBean(PatientService.class);
        Patient patient = patientService.findById(crisis.getPatient());
        Event event = EventFactory.createCrisisEvent("registered");
        patient.triggerEvent(event);
        return repo.save(crisis);
    }

    public void applyMedication(Integer patientId) {
        PatientService patientService = SpringContext.getBean(PatientService.class);
        Patient patient = patientService.findById(patientId);
        Event event = EventFactory.createCrisisEvent("timeExceeded");
        patient.triggerEvent(event);
    }
}
