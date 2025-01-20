package com.example.episafezone.controllers;


import com.example.episafezone.DTO.CrisisDTO.CrisisDTO;
import com.example.episafezone.DTO.CrisisDTO.CrisisListDTO;
import com.example.episafezone.DTO.ManifestationsDTO.NumPerManifestationListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientCrisisListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.exceptions.FormatUnsupportedException;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(path="/numMani/{id}")
    public @ResponseBody NumPerManifestationListDTO getCrisisNumPerMani(@PathVariable Integer id){
        return patientService.numPerManifestation(id);
    }

    @GetMapping(path="/crisisWeek/{id}")
    public @ResponseBody PatientCrisisListDTO getCrisisThisWeek(@PathVariable Integer id){
        return patientService.lastWeekCrisis(id);
    }
    
    @GetMapping(path = "/image/{patientId}")
    public @ResponseBody Resource getImage(@PathVariable Integer patientId) {
        try{
            return patientService.getImage(patientId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path="/image/upload/{patientId}")
    public ResponseEntity<Boolean> uploadImage(@PathVariable Integer patientId, MultipartFile file){
        if(patientService.addImage(patientId, file)){return new ResponseEntity<>(HttpStatus.ACCEPTED);}
        else{return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);}
    }

    @PostMapping(path="/notify/{patientId}")
    public void notifyPatient(@PathVariable Integer patientId, @RequestParam String type, @RequestParam String subtype){
        Patient  patient = patientService.findById(patientId);
        if(type.equalsIgnoreCase("crisis") || type.equalsIgnoreCase("medicine")){
            Event event = EventFactory.createEvent(type, subtype);
            patient.triggerEvent(event);
        }else{
            throw new FormatUnsupportedException("no type suported for notifications");
        }
    }
}
