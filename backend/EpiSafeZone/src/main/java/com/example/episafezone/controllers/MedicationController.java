package com.example.episafezone.controllers;


import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.services.MedicationService;
import com.example.episafezone.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/medication")
public class MedicationController {
    @Autowired
    MedicationService medicationService;

    @GetMapping(path="/all")
    public @ResponseBody List<Medication> getAll(){
        return medicationService.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Medication getAll(@PathVariable int id){return medicationService.findById(id);
    }

    @GetMapping(path="/patient/{patientId}")
    public @ResponseBody List<Medication> getMedicationsByPatient(@PathVariable Integer patientId) {
        return medicationService.findMedicationsByPatient(patientId);
    }

    @PostMapping(path="/create")
    public @ResponseBody Medication createMedication(@RequestBody Medication medication){
        return medicationService.create(medication);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody Medication updateMedication(@PathVariable Integer id,
                                                     @RequestBody Medication medication){
        return medicationService.update(id, medication);
    }


}