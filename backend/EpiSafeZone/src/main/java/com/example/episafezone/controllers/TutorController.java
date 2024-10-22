package com.example.episafezone.controllers;

import com.example.episafezone.DTO.PatientsDTO.PatientListDTO;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.PatientService;
import com.example.episafezone.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/tutor")
public class TutorController {
    @Autowired
    TutorService tutorService;

    @Autowired
    PatientService patientService;

    @GetMapping(path="/all")
    public @ResponseBody List<Tutor> getAll(){
        return tutorService.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Tutor> getAll(@PathVariable int id){
        return tutorService.findById(id);
    }

    @GetMapping(path="/list/{id}")
    public @ResponseBody List<PatientListDTO> getPatientList(@PathVariable Integer id){
        return patientService.getPatientList(id);
    }
}
