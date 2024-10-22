package com.example.episafezone.controllers;

import com.example.episafezone.models.Manifestation;
import com.example.episafezone.services.ManifestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "manifestation")
public class ManifestationController {
    @Autowired
    ManifestationService manifestationService;

    @GetMapping(path = "/{id}")
    public @ResponseBody Manifestation getManifestation(@PathVariable Integer id) {
        return manifestationService.getManifestationById(id);
    }

    @GetMapping(path="/patient/{id}")
    public @ResponseBody List<Manifestation> getPatientManifestation(@PathVariable Integer id) {
        return manifestationService.getManifestationFromPatient(id);
    }

    @PostMapping(path ="/create")
    public @ResponseBody Manifestation createManifestation(@RequestBody Manifestation manifestation) {
        return manifestationService.create(manifestation);
    }

    @PutMapping(path ="/edit/{id}")
    public @ResponseBody Manifestation editManifestation(@PathVariable Integer id, @RequestBody Manifestation manifestation) {
        return manifestationService.update(id,manifestation);
    }

    /*@DeleteMapping(path ="/delete/{id}")
    public @ResponseBody ResponseEntity<Map<String, Object>> deleteManifestation(@PathVariable Integer id) {
        return
    }*/


}
