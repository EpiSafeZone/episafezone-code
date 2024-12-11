package com.example.episafezone.controllers;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.models.Device;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.DeviceRepository;
import com.example.episafezone.services.NotificationService;
import com.example.episafezone.services.PatientService;
import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseTestController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    DeviceRepository deviceRepository;

    @GetMapping("/test-firebase")
    public String testFirebaseInitialization() {
        // Obtén la lista de instancias inicializadas
        if (FirebaseApp.getApps().isEmpty()) {
            return "Firebase no se ha inicializado correctamente.";
        }
        return "Firebase se ha inicializado correctamente: " + FirebaseApp.getInstance().getName();
    }

    @PostMapping(path= "notify/test")
    public @ResponseBody Integer notifyTest() {
        Event event = EventFactory.createCrisisEvent("registered");
        PatientService patientService = SpringContext.getBean(PatientService.class);
        Patient patient = patientService.findById(1);
        return notificationService.TriggerNotifications(event, patient);
    }
}
