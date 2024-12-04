package com.example.episafezone.controllers;


import com.example.episafezone.DTO.CrisisDTO.CrisisDTO;
import com.example.episafezone.DTO.CrisisDTO.CrisisListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public @ResponseBody Patient getAll(@PathVariable int id){
        return patientService.findById(id);
    }

    @GetMapping(path="/info/{patientId}/{userId}")
    public @ResponseBody PatientInfoDTO getPatientInfo(@PathVariable Integer patientId, @PathVariable Integer userId) {
        return patientService.getPatientProfileInfo(patientId, userId);
    }
        @GetMapping(path="/info/manifestations/{id}")
    public @ResponseBody List<HasManifestation> getManifestations(@PathVariable Integer id) {
        return patientService.getPatientManifestations(id);
    }

    @GetMapping(path="/crisis/{id}/{year}/{month}")
    public @ResponseBody CrisisListDTO getCrisisFromPatient(@PathVariable Integer id, @PathVariable Integer year, @PathVariable Integer month){
        return patientService.getListOfCrisis(id, year, month);
    }
}
