package com.example.episafezone.controllers;

import com.example.episafezone.DTO.NotifyHoursDTO;
import com.example.episafezone.DTO.PatientsDTO.PatientListDTO;
import com.example.episafezone.DTO.SharedDTO.IsTutorDTO;
import com.example.episafezone.DTO.SharedDTO.SharePatientDTO;
import com.example.episafezone.DTO.SharedDTO.SharedPermissionsDTO;
import com.example.episafezone.models.NotifyHours;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.services.PatientService;
import com.example.episafezone.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public @ResponseBody Tutor getAll(@PathVariable int id){
        return tutorService.findById(id);
    }

    @GetMapping(path="/list/{id}")
    public @ResponseBody List<PatientListDTO> getPatientList(@PathVariable Integer id){
        return patientService.getPatientList(id);
    }

    @GetMapping(path="/isTutor")
    public @ResponseBody Boolean isTutor(@RequestBody IsTutorDTO isTutorDTO){
        return tutorService.isTutor(isTutorDTO);
    }

    @PostMapping(path="share")
    public @ResponseBody SharedWith sharePatient(@RequestBody SharePatientDTO sharePatientDTO){
        return tutorService.sharePatient(sharePatientDTO);
    }

    @PutMapping(path="editHours")
    public @ResponseBody NotifyHours editNotificationHours(@RequestBody NotifyHoursDTO notifyHoursDTO){
        return tutorService.editNotificationHours(notifyHoursDTO);
    }

    @PutMapping(path="editPermissions")
    public @ResponseBody SharedWith editPermissions(@RequestBody SharedPermissionsDTO sharedPermissionsDTO){
        return tutorService.editPermissions(sharedPermissionsDTO);
    }
}
