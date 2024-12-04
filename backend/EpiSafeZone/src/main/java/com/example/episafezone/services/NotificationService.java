package com.example.episafezone.services;

import com.example.episafezone.events.Event;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private void SerndNotification(Event event, String token){
        
    }

    public static void TirggerNotifications(Event event, Patient patient){
        List<Tutor> tutors = patient.getTutors();
        for (Tutor tutor : tutors) {

        }
    }
}
