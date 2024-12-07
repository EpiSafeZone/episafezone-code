package com.example.episafezone.services;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.CrisisEvent;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.MedicationEvent;
import com.example.episafezone.models.Device;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.repositories.DeviceRepository;
import com.example.episafezone.utils.Research;
import com.example.episafezone.utils.Verify;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    public static String SendNotification(Event event, String token){
        try {
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(event.getTitle())
                            .setBody(event.getDescription())
                            .build())
                    .setToken(token)
                    .build();

            return FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar notificación al tópico", e);
        }
        
    }

    public static Integer TriggerNotifications(Event event, Patient patient){
        Integer response = 0;
        DeviceRepository deviceRepository = SpringContext.getBean(DeviceRepository.class);
        List<Tutor> tutors = patient.getTutors();
        for (Tutor tutor : tutors) {
            Map<String, Boolean> permissions = Research.ResearchPermissions(patient, tutor);
            if(Verify.VerifyHoursOfNotification(event, tutor, patient) != null && Verify.VerifyHoursOfNotification(event, tutor, patient)) {
                if (event instanceof MedicationEvent) {
                    MedicationEvent medicationEvent = (MedicationEvent) event;
                    if (permissions.get("Medication")) {
                        List<Device> deviceList = deviceRepository.findDevicesByUser(tutor.getId());
                        for (Device device : deviceList) {
                            SendNotification(medicationEvent, device.getToken());
                            response += 1;
                        }
                    }
                }else{
                    CrisisEvent crisisEvent = (CrisisEvent) event;
                    List<Device> deviceList = deviceRepository.findDevicesByUser(tutor.getId());
                    for (Device device : deviceList) {
                        SendNotification(crisisEvent, device.getToken());
                        response += 1;
                    }
                }
            }
        }
        return response;
    }
}
