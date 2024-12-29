package com.example.episafezone.services;

import com.example.episafezone.DTO.NotifyHoursDTO;
import com.example.episafezone.DTO.SharedDTO.GetPermissionsDTO;
import com.example.episafezone.DTO.SharedDTO.IsTutorDTO;
import com.example.episafezone.DTO.SharedDTO.SharePatientDTO;
import com.example.episafezone.DTO.SharedDTO.SharedPermissionsDTO;
import com.example.episafezone.exceptions.AlreadySharedWithException;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.NotifyHours;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TutorService implements TutorServiceInterface {
    @Autowired
    TutorRepository tutorRepo;

    @Autowired
    @Lazy
    SharedWithService sharedWithService;

    @Autowired
    NotifyHoursService notifyHoursService;

    @Autowired
    SharedWithRepository sharedWithRepo;

    @Autowired
    TutorOfService tutorOfService;

    @Override
    public List<Tutor> findAll() {
        return tutorRepo.findAll();
    }

    @Override
    public Tutor findById(int id) {
        if (tutorRepo.findById(id).isPresent()) {
            return tutorRepo.findById(id).get();
        } else {
            throw new ResourceNotFoudException("No se ha encontrdo un tutor para el id: " + id);
        }
    }

    public List<Tutor> findTutorsShared(Integer tutorShId, Integer patient) {
        List<SharedWith> sharedWithList = sharedWithService.findByTutorShAndPatient(tutorShId, patient);
        List<Integer> tutorSharedIds = sharedWithList.stream()
                .map(SharedWith::getTutorReceiving)
                .toList();
        List<Tutor> tutorsShared = tutorSharedIds.stream()
                .map(tutorId -> tutorRepo.findById(tutorId).orElse(null))
                .toList();
        return tutorsShared;
    }

    public Tutor findTutorByEmail(String email) {
        return tutorRepo.findByEmail(email);
    }

    public SharedPermissionsDTO getPermissions(GetPermissionsDTO getPermissionsDTO) {
        return sharedWithService.getPermissions(getPermissionsDTO);
    }

    public NotifyHoursDTO getHours(IsTutorDTO isTutorDTO) {
        return notifyHoursService.getHours(isTutorDTO);
    }

    public SharedWith sharePatient(SharePatientDTO sharePatientDTO) {

        Tutor tutorReceiving = findTutorByEmail(sharePatientDTO.getTutorReceivingEmail());

        if (tutorReceiving == null) {
            throw new ResourceNotFoudException("El email no pertenece a ningún tutor");
        }

        Integer tutorReceivingId = tutorReceiving.getId();

        //Comprobamos que no se le haya compartido el paciente ya al tutor que recibe.
        List<SharedWith> existingSharedWith = sharedWithService.findByTutorShAndPatient(
                tutorReceivingId,
                sharePatientDTO.getPatientId()
        );
        //Si se le ha compartido devolvemos null
        if (!existingSharedWith.isEmpty()) {
            throw new AlreadySharedWithException("You have already shared the patient with this tutor");
        }

        // Verificar si el tutor que recibe es el tutor que creo el perfil del paciente
        boolean existsInTutorOf = tutorOfService.findAll().stream().anyMatch(tutorOf ->
                tutorOf.getTutor().equals(tutorReceivingId) &&
                        tutorOf.getPatient().equals(sharePatientDTO.getPatientId())
        );
        if (existsInTutorOf) {
            return null;
        }

        if (sharePatientDTO.getTutorPermission()) {
            tutorOfService.addTutorOf(
                    tutorReceivingId,
                    sharePatientDTO.getPatientId(),
                    false
            );
        }

        NotifyHoursDTO notifyHoursDTO = new NotifyHoursDTO(tutorReceivingId,
                sharePatientDTO.getPatientId(),
                LocalTime.of(0, 0, 0),
                LocalTime.of(23, 59, 59)
        );
        notifyHoursService.addNotifyHours(notifyHoursDTO);

        SharedWith sharedWith = new SharedWith(sharePatientDTO.getTutorSharingId(), tutorReceivingId,sharePatientDTO.getPatientId(),
                sharePatientDTO.getRegisterCrisisPermission(), sharePatientDTO.getProfilePermission(), sharePatientDTO.getMedicinePermission(),
                sharePatientDTO.getTutorPermission());

        sharedWithService.sharePatient(sharedWith);

        return sharedWith;
    }

    public NotifyHours editNotificationHours(NotifyHoursDTO notifyHoursDTO) {
        return notifyHoursService.editNotifyHours(notifyHoursDTO);
    }

    public SharedWith editPermissions(SharedPermissionsDTO sharedPermissionsDTO) {
        SharedWith sharedWith = sharedWithRepo.findByTutorReceivingAndPatient(
                sharedPermissionsDTO.getTutorReciving(),
                sharedPermissionsDTO.getPatient()
        );
        Boolean isTutor = sharedWith.getTutorPermision();
        Boolean willBeTutor = sharedPermissionsDTO.getTutorPermision();
        if (isTutor && !willBeTutor) {
            tutorOfService.deleteTutorOf(
                    sharedPermissionsDTO.getTutorReciving(),
                    sharedPermissionsDTO.getPatient()
            );
        } else if (!isTutor && willBeTutor) {
            tutorOfService.addTutorOf(
                    sharedPermissionsDTO.getTutorReciving(),
                    sharedPermissionsDTO.getPatient(),
                    false
            );
        }
        sharedWith.setRegisterCrisisPermision(sharedPermissionsDTO.getRegisterCrisisPermision());
        sharedWith.setProfilePermision(sharedPermissionsDTO.getProfilePermision());
        sharedWith.setMedicinePermision(sharedPermissionsDTO.getMedicinePermision());
        sharedWith.setTutorPermision(sharedPermissionsDTO.getTutorPermision());
        sharedWithRepo.save(sharedWith);
        return sharedWith;
    }

    public Boolean isTutor(IsTutorDTO isTutorDTO) {
        if (tutorOfService.findByTutorAndPatient(isTutorDTO.getTutor(), isTutorDTO.getPatient()) == null) {
            return false;
        } else {
            return true;
        }
    }
}