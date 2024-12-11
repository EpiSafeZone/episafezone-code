package com.example.episafezone.controllers;


import com.example.episafezone.DTO.CrisisDTO.CrisisDTO;
import com.example.episafezone.DTO.CrisisDTO.CrisisListDTO;
import com.example.episafezone.DTO.ManifestationsDTO.NumPerManifestationListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientCrisisListDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientInfoDTO;
import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return patientService.getImage(patientId);
    }
    
/*@PostMapping(path = "/image/add/{patientId}")
    public @ResponseEntity<?> addimage(@PathVariable Integer patientId, @RequestParam("file") MultipartFile file){
        return null;
    }
 */
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
        return patientService.getImage(patientId);
    }

    @PostMapping(path = "/image/add/{patientId}")
    public @ResponseEntity<?> addimage(@PathVariable Integer patientId, @RequestParam("file") MultipartFile file){
        return
    }
}
