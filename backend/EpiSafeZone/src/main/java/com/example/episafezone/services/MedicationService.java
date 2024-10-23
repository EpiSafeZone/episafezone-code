package com.example.episafezone.services;

import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.MedicationRepository;
import com.example.episafezone.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService implements MedicationServiceInterface{
    @Autowired
    MedicationRepository medicationRepo;

    @Autowired
    PatientRepository patientRepo;


    @Override
    public List<Medication> findAll() {
        return medicationRepo.findAll();
    }

    @Override
    public Medication findById(int id) {
        Optional<Medication> medication =  medicationRepo.findById(id);
        if(medication.isPresent()){
            return medication.get();
        }else{
            throw new ResourceNotFoudException("No se ha encontrado la medication con el id: " + id);
        }
    }

    @Override
    public List<Medication> findMedicationsByPatient(Integer patientId) {
        return medicationRepo.findByPatientMedicated(patientId);
    }

    public Medication create(Medication medication){
        return medicationRepo.save(medication);
    }

    public Medication update(Integer id, Medication medication){
        Medication toUpdate = findById(id);
        toUpdate.setName(medication.getName());
        toUpdate.setDosis(medication.getDosis());
        toUpdate.setUnit(medication.getUnit());
        toUpdate.setAlarm(medication.getAlarm());
        toUpdate.setPatientMedicated(medication.getPatientMedicated());
        return medicationRepo.save(toUpdate);
    }

}
