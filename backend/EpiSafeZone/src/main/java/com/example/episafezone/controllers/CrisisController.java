package com.example.episafezone.controllers;

import com.example.episafezone.models.Crisis;
import com.example.episafezone.services.CrisisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/crisis")
public class CrisisController {
    private final CrisisService crisisService;
    public CrisisController(CrisisService crisisService) {
        this.crisisService = crisisService;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Crisis getCrisis(@PathVariable Integer id) {
        return crisisService.getById(id);
    }
}
