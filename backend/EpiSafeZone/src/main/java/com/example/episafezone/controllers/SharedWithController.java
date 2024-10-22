package com.example.episafezone.controllers;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.services.SharedWithService;
import com.example.episafezone.services.TutorOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/sharedWith")
public class SharedWithController {
    @Autowired
    SharedWithService sharedWithService;

    @GetMapping(path="/all")
    public @ResponseBody List<SharedWith> getAll(){
        return sharedWithService.findAll();
    }

    @GetMapping(path="/tutorReceiving/{tutorId}")
    public @ResponseBody List<SharedWith> getAllByTutorReceiving(@PathVariable Integer tutorId) {
        return sharedWithService.findByTutorReceiving(tutorId);
    }

    @GetMapping(path="/patientsShared/{tutorId}")
    public @ResponseBody List<Integer> getPatientsByTutorReceiving(@PathVariable Integer tutorId) {
        return sharedWithService.findPByTReceiving(tutorId);
    }
}
