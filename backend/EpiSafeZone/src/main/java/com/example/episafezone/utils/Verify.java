package com.example.episafezone.utils;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import com.example.episafezone.models.NotifyHours;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.repositories.NotifyHoursRepository;

public class Verify {

    public static Boolean VerifyHoursOfNotification(Event event, Tutor tutor, Patient patient){
        NotifyHoursRepository notifyHoursRepository = SpringContext.getBean(NotifyHoursRepository.class);
        NotifyHours notifyHours = notifyHoursRepository.findByPatientAndTutor(patient.getId(), tutor.getId());
        if(notifyHours != null){
            if(event.getTime().isAfter(notifyHours.getNotifyFrom()) && event.getTime().isBefore(notifyHours.getNotifyTo())){
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }else{
            return null;
        }
    }
}
