package com.example.episafezone.controllers;

import com.google.firebase.FirebaseApp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseTestController {

    @GetMapping("/test-firebase")
    public String testFirebaseInitialization() {
        // Obtén la lista de instancias inicializadas
        if (FirebaseApp.getApps().isEmpty()) {
            return "Firebase no se ha inicializado correctamente.";
        }
        return "Firebase se ha inicializado correctamente: " + FirebaseApp.getInstance().getName();
    }
}
