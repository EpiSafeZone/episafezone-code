package com.example.episafezone.services;

import com.example.episafezone.DTO.SharedDTO.SharePatientDTO;
import com.example.episafezone.DTO.SharedDTO.SharedPermissionsDTO;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.repositories.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorServiceTest {

    @Mock
    TutorRepository tutorRepository;

    @Mock
    SharedWithService sharedWithService;

    @Mock
    SharedWithRepository sharedWithRepository;

    @Mock
    NotifyHoursService notifyHoursService;

    @Mock
    TutorOfService tutorOfService;

    @InjectMocks
    TutorService tutorService;

    @Test
    public void editPermissionWithOutConvertingToTutor(){
        SharedPermissionsDTO sharedPermissionsDTO = new SharedPermissionsDTO(1,2,true, false, false, false);
        SharedWith sharedWith = new SharedWith(2,1,2, true, true, true, false);

        when(sharedWithRepository.findByTutorReceivingAndPatient(1,2)).thenReturn(sharedWith);

        SharedWith response = tutorService.editPermissions(sharedPermissionsDTO);

        assertTrue(response.getRegisterCrisisPermision());
        assertFalse(response.getProfilePermision());
        assertFalse(response.getMedicinePermision());
        assertEquals(response.getTutorPermision(), sharedWith.getTutorPermision());
    }

    @Test
    public void editPermissionsAndRemoveFromTutorOf(){
        SharedPermissionsDTO sharedPermissionsDTO = new SharedPermissionsDTO(1,2,true, false, false, false);
        SharedWith sharedWith = new SharedWith(2,1,2, true, true, true, true);

        when(sharedWithRepository.findByTutorReceivingAndPatient(1,2)).thenReturn(sharedWith);

        SharedWith sharedWithResponse = tutorService.editPermissions(sharedPermissionsDTO);

        assertFalse(sharedWithResponse.getTutorPermision());
        assertTrue(sharedWithResponse.getRegisterCrisisPermision());
        assertFalse(sharedWithResponse.getProfilePermision());
        assertFalse(sharedWithResponse.getMedicinePermision());

        verify(tutorOfService).deleteTutorOf(1,2);
        verify(sharedWithRepository).save(sharedWith);
    }

    @Test
    public void editPermissionsAndAddToTutorOf(){
        SharedPermissionsDTO sharedPermissionsDTO = new SharedPermissionsDTO(1,2,true, false, false, true);
        SharedWith sharedWith = new SharedWith(2,1,2, true, true, true, false);

        when(sharedWithRepository.findByTutorReceivingAndPatient(1,2)).thenReturn(sharedWith);
        SharedWith sharedWithResponse = tutorService.editPermissions(sharedPermissionsDTO);

        assertTrue(sharedWithResponse.getTutorPermision());
        assertTrue(sharedWithResponse.getRegisterCrisisPermision());
        assertFalse(sharedWithResponse.getProfilePermision());
        assertFalse(sharedWithResponse.getMedicinePermision());

        verify(tutorOfService).addTutorOf(1,2, false);

        verify(sharedWithRepository).save(sharedWith);
    }

    @Test
    public void sharePatient_NoTutorPermission_Succes(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreciving@gmail.com", 3, true, false, false, false);
        Tutor tutorReciving = new Tutor("tutor2", "reciving","tutorreciving@gmail.com", "reciving", true);
        tutorReciving.setId(2);
        Patient patientSharing = new Patient("patient","shared", 100,50, new Date(12), 15, "imagenURL");
        patientSharing.setId(3);
        TutorOf tutorOf = new TutorOf(1,3,false);
        SharedWith resultExpected = new SharedWith(1,2,3,true, false,false, false);


        when(tutorRepository.findByEmail("tutorreciving@gmail.com")).thenReturn(tutorReciving);
        when(sharedWithService.findByTutorShAndPatient(tutorReciving.getId(), sharePatientDTO.getPatientId())).thenReturn(Arrays.asList());
        when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf));

        SharedWith resultObtained = tutorService.sharePatient(sharePatientDTO);

        assertEquals(resultExpected.getTutorSharing(),resultObtained.getTutorSharing());
        assertEquals(resultExpected.getTutorReceiving(), resultObtained.getTutorReceiving());
        assertEquals(resultExpected.getPatient(), resultObtained.getPatient());
        assertEquals(resultExpected.getRegisterCrisisPermision(), resultObtained.getRegisterCrisisPermision());
        assertEquals(resultExpected.getProfilePermision(), resultObtained.getProfilePermision());
        assertEquals(resultExpected.getMedicinePermision(), resultObtained.getMedicinePermision());
        assertEquals(resultExpected.getTutorPermision(), resultObtained.getTutorPermision());




    }
}
