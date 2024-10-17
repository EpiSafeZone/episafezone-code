package com.example.episafezone.controllers;

import com.example.episafezone.models.Manifestation;
import com.example.episafezone.services.ManifestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "manifestation")
public class ManifestationController {
    @Autowired
    ManifestationService manifestationService;

    @GetMapping(path = "/{id}")
    public @ResponseBody Manifestation getManifestation(@PathVariable Integer id) {
        return manifestationService.getManifestationById(id);
    }
}
