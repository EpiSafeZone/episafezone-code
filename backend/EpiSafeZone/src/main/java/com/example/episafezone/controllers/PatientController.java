package com.example.episafezone.controllers;


import com.example.episafezone.models.Patient;
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
@RequestMapping(path="/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping(path="/all")
    public @ResponseBody List<Patient> getAll(){
        return patientService.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Patient> getAll(@PathVariable int id){
        return patientService.findById(id);
    }
}