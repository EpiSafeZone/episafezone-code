package com.example.episafezone.controllers;

import com.example.episafezone.DTO.ManifestationsDTO.ManifestationRequestDTO;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.services.ManifestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "manifestation")
public class ManifestationController {

    @Autowired
    ManifestationService manifestationService;

    @GetMapping(path = "/{id}")
    public @ResponseBody Manifestation getManifestation(@PathVariable Integer id) {
        return manifestationService.getManifestationById(id);
    }

    @GetMapping(path = "/patient/{patientId}")
    public @ResponseBody List<Manifestation> getManifestationByPatient(@PathVariable Integer patientId){
        return manifestationService.getManifestationFromPatient(patientId);
    }

    @PostMapping(path="/create")
    public @ResponseBody Manifestation createManifestation(@RequestBody ManifestationRequestDTO manifestation) {
        return manifestationService.create(manifestation);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody Manifestation editManifestation(@PathVariable Integer id, @RequestBody Manifestation manifestation) {
        return manifestationService.update(id, manifestation);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Void> deleteManifestation(@PathVariable Integer id) {
        manifestationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
