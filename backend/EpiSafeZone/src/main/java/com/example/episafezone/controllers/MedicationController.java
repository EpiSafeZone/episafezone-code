package com.example.episafezone.controllers;


import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Patient;
import com.example.episafezone.services.MedicationService;
import com.example.episafezone.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody Optional<Medication> getAll(@PathVariable int id){return medicationService.findById(id);
    }

    @GetMapping(path="/patient/{patientId}")
    public @ResponseBody List<Medication> getMedicationsByPatient(@PathVariable Integer patientId) {
        return medicationService.findMedicationsByPatient(patientId);
    }


}