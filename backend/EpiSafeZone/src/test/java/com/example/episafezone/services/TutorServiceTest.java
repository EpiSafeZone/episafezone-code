package com.example.episafezone.services;

import com.example.episafezone.DTO.NotifyHoursDTO;
import com.example.episafezone.DTO.SharedDTO.SharePatientDTO;
import com.example.episafezone.DTO.SharedDTO.SharedPermissionsDTO;
import com.example.episafezone.exceptions.AlreadySharedWithException;
import com.example.episafezone.exceptions.ResourceNotFoudException;
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

import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

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
    public void sharePatient_NoTutorPermission_Success(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreceiving@gmail.com", 3, true, false, false, false);
        Tutor tutorReceiving = new Tutor("tutor2", "receiving","tutorreceiving@gmail.com", "receiving", true);
        tutorReceiving.setId(2);
        Patient patientSharing = new Patient("patient","shared", 100,50, new Date(12), 15, "imagenURL");
        patientSharing.setId(3);
        TutorOf tutorOf = new TutorOf(1,3,false);
        SharedWith resultExpected = new SharedWith(1,2,3,true, false,false, false);

        when(tutorRepository.findByEmail("tutorreceiving@gmail.com")).thenReturn(tutorReceiving);
        when(sharedWithService.findByTutorShAndPatient(tutorReceiving.getId(), sharePatientDTO.getPatientId())).thenReturn(Arrays.asList());
        when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf));

        SharedWith resultObtained = tutorService.sharePatient(sharePatientDTO);

        assertEquals(resultExpected.getTutorSharing(),resultObtained.getTutorSharing());
        assertEquals(resultExpected.getTutorReceiving(), resultObtained.getTutorReceiving());
        assertEquals(resultExpected.getPatient(), resultObtained.getPatient());
        assertEquals(resultExpected.getRegisterCrisisPermision(), resultObtained.getRegisterCrisisPermision());
        assertEquals(resultExpected.getProfilePermision(), resultObtained.getProfilePermision());
        assertEquals(resultExpected.getMedicinePermision(), resultObtained.getMedicinePermision());
        assertEquals(resultExpected.getTutorPermision(), resultObtained.getTutorPermision());

        verify(notifyHoursService).addNotifyHours(any());
    }

    @Test
    public void SharePatientTest_WithTutorPermission_Success(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreceiving@gmail.com", 3, true, false, false, true);
        Tutor tutorReceiving = new Tutor("tutor2", "receiving","tutorreceiving@gmail.com", "receiving", true);
        tutorReceiving.setId(2);
        Patient patientSharing = new Patient("patient","shared", 100,50, new Date(12), 15, "imagenURL");
        patientSharing.setId(3);
        TutorOf tutorOf = new TutorOf(1,3,false);
        SharedWith resultExpected = new SharedWith(1,2,3,true, false,false, true);

        when(tutorService.findTutorByEmail("tutorreceiving@gmail.com")).thenReturn(tutorReceiving);
        when(sharedWithService.findByTutorShAndPatient(tutorReceiving.getId(), sharePatientDTO.getPatientId())).thenReturn(Arrays.asList());
        when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf));

        SharedWith resultObtained = tutorService.sharePatient(sharePatientDTO);

        verify(tutorOfService).addTutorOf(2,3,false);

        assertEquals(resultExpected.getTutorSharing(),resultObtained.getTutorSharing());
        assertEquals(resultExpected.getTutorReceiving(), resultObtained.getTutorReceiving());
        assertEquals(resultExpected.getPatient(), resultObtained.getPatient());
        assertEquals(resultExpected.getRegisterCrisisPermision(), resultObtained.getRegisterCrisisPermision());
        assertEquals(resultExpected.getProfilePermision(), resultObtained.getProfilePermision());
        assertEquals(resultExpected.getMedicinePermision(), resultObtained.getMedicinePermision());
        assertEquals(resultExpected.getTutorPermision(), resultObtained.getTutorPermision());

        verify(notifyHoursService).addNotifyHours(any());
    }

    @Test
    public void SharePatientTest_ThrowsResourceNotFoundException(){
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreceiving@gmail.com", 3, true, false, false, true);
        Tutor tutorReceiving = new Tutor("tutor2", "receiving","tutorreceiving@gmail.com", "receiving", true);
        tutorReceiving.setId(2);

        when(tutorRepository.findByEmail(sharePatientDTO.getTutorReceivingEmail())).thenReturn(null);

        assertThrows(ResourceNotFoudException.class, () -> tutorService.sharePatient(sharePatientDTO));
    }

    @Test
    public void SharePatientTest_ThrowsAlreadySharedWithException(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreceiving@gmail.com", 3, true, false, false, true);
        Tutor tutorReceiving = new Tutor("tutor2", "receiving","tutorreceiving@gmail.com", "receiving", true);
        tutorReceiving.setId(2);
        Patient patientSharing = new Patient("patient","shared", 100,50, new Date(12), 15, "imagenURL");
        patientSharing.setId(3);
        TutorOf tutorOf = new TutorOf(1,3,true);

        SharedWith sharedWith = new SharedWith(1,2,3,true, false,false, true);

        when(tutorService.findTutorByEmail("tutorreceiving@gmail.com")).thenReturn(tutorReceiving);
        when(sharedWithService.findByTutorShAndPatient(tutorReceiving.getId(), sharePatientDTO.getPatientId())).thenReturn(Arrays.asList(sharedWith));

        assertThrows(AlreadySharedWithException.class, () -> tutorService.sharePatient(sharePatientDTO));
    }

    @Test
    public void ShareWithTest_ReturnNullWhenIsParent(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        SharePatientDTO sharePatientDTO = new SharePatientDTO(1,"tutorreceiving@gmail.com", 3, true, false, false, true);
        Tutor tutorReceiving = new Tutor("tutor2", "receiving","tutorreceiving@gmail.com", "receiving", true);
        tutorReceiving.setId(2);
        Patient patientSharing = new Patient("patient","shared", 100,50, new Date(12), 15, "imagenURL");
        patientSharing.setId(3);
        TutorOf tutorOf = new TutorOf(2,3,true);

        when(tutorService.findTutorByEmail("tutorreceiving@gmail.com")).thenReturn(tutorReceiving);
        when(sharedWithService.findByTutorShAndPatient(tutorReceiving.getId(), sharePatientDTO.getPatientId())).thenReturn(Arrays.asList());
        when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf));

        SharedWith response = tutorService.sharePatient(sharePatientDTO);
        assertNull(response);
    }

    @Test
    public void FindByIdTest_Success(){
        Tutor tutor = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutor.setId(1);
        when(tutorRepository.findById(1)).thenReturn(Optional.of(tutor));

        Tutor reponse = tutorService.findById(1);

        assertEquals(tutor,reponse);
    }

    @Test
    public void FindByIdTest_ThrowsResourceNotFoundException(){
        when(tutorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoudException.class, () -> tutorService.findById(1));
    }

    @Test
    public void FindTutorsSharedTest_Success(){
        Tutor tutorSharing = new Tutor("tutor", "Sharing", "tutorsharing@gmail.com", "sharing", false);
        tutorSharing.setId(1);
        Tutor tutorShared1 = new Tutor("tutor2", "I've benn Shared", "beenShared2@gmail.com", "1234", true);
        tutorShared1.setId(2);
        Tutor tutorShared2 = new Tutor("tutor3", "I've benn Shared", "beenShared3@gmail.com", "1234", true);
        tutorShared2.setId(3);
        Tutor tutorNotShared = new Tutor("tutor4", "I've not benn Shared", "notbeenShared3@gmail.com", "1234", true);
        tutorNotShared.setId(4);

        SharedWith sharedTutor2 = new SharedWith(1,2,5,true, false,false, true);
        SharedWith sharedTutor3 = new SharedWith(1,3,5,true, false,false, true);

        when(sharedWithService.findByTutorShAndPatient(tutorSharing.getId(), 5)).thenReturn(Arrays.asList(sharedTutor2, sharedTutor3));
        when(tutorRepository.findById(2)).thenReturn(Optional.of(tutorShared1));
        when(tutorRepository.findById(3)).thenReturn(Optional.of(tutorShared2));

        List<Tutor> response = tutorService.findTutorsShared(tutorSharing.getId(), 5);

        assertEquals(2, response.size());
        assertTrue(response.contains(tutorShared1));
        assertTrue(response.contains(tutorShared2));
        assertFalse(response.contains(tutorNotShared));
    }
}