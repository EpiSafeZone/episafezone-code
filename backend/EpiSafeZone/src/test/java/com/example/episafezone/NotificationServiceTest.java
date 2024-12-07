package com.example.episafezone;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.events.MedicationEvent;
import com.example.episafezone.models.*;
import com.example.episafezone.repositories.DeviceRepository;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.repositories.TutorOfRepository;
import com.example.episafezone.services.NotificationService;
import com.example.episafezone.utils.Research;
import com.example.episafezone.utils.Verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationServiceTest {

    private DeviceRepository mockDeviceRepository;
    private Patient mockPatient;
    private Tutor tutor1, tutor2, tutor3;
    private Device device11, device12, device21, device22, device31, device32;

    @BeforeEach
    public void setUp(){
        mockDeviceRepository = mock(DeviceRepository.class);
        mockPatient = mock(Patient.class);
        tutor1 = mock(Tutor.class);
        tutor2 = mock(Tutor.class);
        tutor3 = mock(Tutor.class);
        device11 = mock(Device.class);
        device12 = mock(Device.class);
        device21 = mock(Device.class);
        device22 = mock(Device.class);
        device31 = mock(Device.class);
        device32 = mock(Device.class);
    }

    @Test
    void testTriggerNotificationEventMedication(){
        Event medicationEvent = EventFactory.createMedicationEvent("taken");
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class);
            MockedStatic<Research> mockedResearch = Mockito.mockStatic(Research.class);
            MockedStatic<Verify> mockedVerify = Mockito.mockStatic(Verify.class)){

            TutorOfRepository mockTutorOfRepository = mock(TutorOfRepository.class);
            SharedWithRepository mockSharedWithRepository = mock(SharedWithRepository.class);
            NotificationService notificationService = SpringContext.getBean(NotificationService.class);
            mockedSpringContext.when(() -> SpringContext.getBean(DeviceRepository.class)).thenReturn(mockDeviceRepository);

            when(mockPatient.getTutors()).thenReturn(asList(tutor1, tutor2, tutor3));
            Map<String, Boolean> permissionsTutor1 = Map.of(
                    "Crisis", true,
                    "Medication", true,
                    "Profile", true
            );
            Map<String, Boolean> permissionsTutor2 = Map.of(
                    "Crisis", true,
                    "Medication", true,
                    "Profile", false
            );
            Map<String, Boolean> permissionsTutor3 = Map.of(
                    "Crisis", true,
                    "Medication", false,
                    "Profile", false
            );

            mockedResearch.when(() -> Research.ResearchPermissions(mockPatient, tutor1)).thenReturn(permissionsTutor1);
            mockedResearch.when(() -> Research.ResearchPermissions(mockPatient, tutor2)).thenReturn(permissionsTutor2);
            mockedResearch.when(() -> Research.ResearchPermissions(mockPatient, tutor3)).thenReturn(permissionsTutor3);


            when(mockPatient.getId()).thenReturn(2);
            
            
            when(tutor1.getId()).thenReturn(1);
            when(tutor2.getId()).thenReturn(2);
            when(tutor3.getId()).thenReturn(3);

            when(Verify.VerifyHoursOfNotification(medicationEvent, tutor1, mockPatient)).thenReturn(Boolean.TRUE);
            when(Verify.VerifyHoursOfNotification(medicationEvent, tutor2, mockPatient)).thenReturn(Boolean.TRUE);
            when(Verify.VerifyHoursOfNotification(medicationEvent, tutor3, mockPatient)).thenReturn(Boolean.TRUE);

            when(mockDeviceRepository.findDevicesByUser(1)).thenReturn(asList(device11, device12));
            when(mockDeviceRepository.findDevicesByUser(2)).thenReturn(asList(device21, device22));
            when(mockDeviceRepository.findDevicesByUser(3)).thenReturn(asList(device31, device32));

            Integer response = notificationService.TriggerNotifications(medicationEvent, mockPatient);

            assertEquals(4, response);

        }
    }


}
