package com.example.episafezone.services;

import com.example.episafezone.DTO.NotifyHoursDTO;
import com.example.episafezone.models.NotifyHours;
import com.example.episafezone.repositories.NotifyHoursRepository;
import com.example.episafezone.repositories.TutorOfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyHoursService implements NotifyHoursServiceInterface{

    @Autowired
    NotifyHoursRepository notifyHoursRepo;

    public NotifyHours addNotifyHours(NotifyHoursDTO notifyHoursDTO){
        NotifyHours notifyHours = new NotifyHours(
                notifyHoursDTO.getTutor(),
                notifyHoursDTO.getPatient(),
                notifyHoursDTO.getNotifyFrom(),
                notifyHoursDTO.getNotifyTo()
        );
        return notifyHoursRepo.save(notifyHours);
    }
}
