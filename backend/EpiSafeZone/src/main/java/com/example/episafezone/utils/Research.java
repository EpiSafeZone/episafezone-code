package com.example.episafezone.utils;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.repositories.TutorOfRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Research {

    public static Map<String, Boolean> ResearchPermissions(Patient patient, Tutor tutor){
        TutorOfRepository tutorOfRepository = SpringContext.getBean(TutorOfRepository.class);
        SharedWithRepository sharedWithRepository = SpringContext.getBean(SharedWithRepository.class);
        Map<String, Boolean> permissions = new HashMap<>();
        List<TutorOf> tutorOfList = tutorOfRepository.findAll();
        for(TutorOf tutorOf : tutorOfList){
            if(tutorOf.getTutor().equals(tutor.getId()) && tutorOf.getPatient().equals(patient.getId())){
                permissions.put("Crisis", true);
                permissions.put("Medication", true);
                permissions.put("Profile", true);

                return permissions;
            }
        }

        List<SharedWith> sharedWithList = sharedWithRepository.findAll();
        for(SharedWith sharedWith : sharedWithList){
            if(sharedWith.getTutorReceiving().equals(tutor.getId()) && sharedWith.getPatient().equals(patient.getId())){
                permissions.put("Crisis", sharedWith.getRegisterCrisisPermision());
                permissions.put("Medication", sharedWith.getMedicinePermision());
                permissions.put("Profile", sharedWith.getProfilePermision());
                return permissions;
            }
        }

        return permissions;
    }
}
