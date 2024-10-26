package com.example.episafezone.controllers;

import com.example.episafezone.models.Crisis;
import com.example.episafezone.services.CrisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/crisis")
public class CrisisController {
    @Autowired
    private CrisisService crisisService;

    @GetMapping(path="/{id}")
    public @ResponseBody Crisis getCrisis(@PathVariable Integer id) {
        return crisisService.getById(id);
    }

    @PostMapping(path = "/create")
    public @ResponseBody Crisis createCrisis(@RequestBody Crisis crisis) {
        return crisisService.create(crisis);
    }
}
