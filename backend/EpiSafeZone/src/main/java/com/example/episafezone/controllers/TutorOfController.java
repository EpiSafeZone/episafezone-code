package com.example.episafezone.controllers;

import com.example.episafezone.models.Medication;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.services.TutorOfService;
import com.example.episafezone.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/tutorOf")
public class TutorOfController {
    @Autowired
    TutorOfService tutorOfService;

    @GetMapping(path="/all")
    public @ResponseBody List<TutorOf> getAll(){
        return tutorOfService.findAll();
    }

    @GetMapping(path="/tutor/{tutorId}")
    public @ResponseBody List<TutorOf> getAllByTutorId(@PathVariable Integer tutorId) {
        return tutorOfService.findByTutor(tutorId);
    }

    @GetMapping(path="/tutorP/{tutorId}")
    public @ResponseBody List<Integer> getPatientsByTutor(@PathVariable Integer tutorId) {
        return tutorOfService.findPatientsByTutor(tutorId);
    }
}

