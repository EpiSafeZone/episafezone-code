package com.example.episafezone.controllers;

import com.example.episafezone.models.Crisis;
import com.example.episafezone.services.CrisisService;
import com.example.episafezone.services.ManifestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/crisis")
public class CrisisController {
    @Autowired
    private CrisisService crisisService;
    @Autowired
    private ManifestationService manifestationService;

    @GetMapping(path="/{id}")
    public @ResponseBody Crisis getCrisis(@PathVariable Integer id) {
        return crisisService.getById(id);
    }

    @PostMapping(path = "/create")
    public @ResponseBody Crisis createCrisis(@RequestBody Crisis crisis) {
        return crisisService.create(crisis);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody Crisis updateCrisis(@PathVariable Integer id,
                                             @RequestBody Crisis crisis) {
        return crisisService.update(id,crisis);
    }

    @DeleteMapping(path ="/delete/{id}")
    public ResponseEntity<Void> deleteCrisis(@PathVariable Integer id) {
        crisisService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
