package com.example.episafezone.controllers;

import com.example.episafezone.models.Manifestation;
import com.example.episafezone.services.ManifestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "manifestation")
public class ManifestationController {
    @Autowired
    ManifestationService manifestationService;

    @GetMapping(path = "/{id}")
    public @ResponseBody Manifestation getManifestation(@PathVariable Integer id) {
        return manifestationService.getManifestationById(id);
    }

    @PostMapping(path="/create")
    public @ResponseBody Manifestation createManifestation(@RequestBody Manifestation manifestation) {
        return manifestationService.create(manifestation);
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody Manifestation editManifestation(@PathVariable Integer id, @RequestBody Manifestation manifestation) {
        return manifestationService.update(id, manifestation);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Void> deleteManifestation(@PathVariable Integer id) {
        manifestationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
