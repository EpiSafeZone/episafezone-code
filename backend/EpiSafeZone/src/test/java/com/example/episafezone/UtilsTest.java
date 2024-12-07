package com.example.episafezone;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import com.example.episafezone.models.*;
import com.example.episafezone.repositories.NotifyHoursRepository;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.repositories.TutorOfRepository;
import com.example.episafezone.utils.Research;
import com.example.episafezone.utils.Verify;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilsTest {
    private NotifyHoursRepository mockNotifyHoursRepository;
    private Event mockEvent;
    private Tutor mockTutor;
    private Patient mockPatient;

    private TutorOfRepository mockTutorOfRepository;
    private SharedWithRepository mockSharedWithRepository;
    private TutorOf mockTutorOf;
    private SharedWith mockSharedWith;

    public void setUpVerify() {
        mockNotifyHoursRepository = mock(NotifyHoursRepository.class);
        mockEvent = mock(Event.class);
        mockTutor = mock(Tutor.class);
        mockPatient = mock(Patient.class);
    }

    public void setUpResearch(){
        mockSharedWithRepository = mock(SharedWithRepository.class);
        mockTutorOfRepository = mock(TutorOfRepository.class);
        mockTutor = mock(Tutor.class);
        mockSharedWith = mock(SharedWith.class);
        mockTutorOf = mock(TutorOf.class);
        mockPatient = mock(Patient.class);
    }

    @Test
    public void testVerifyHoursWithinRange(){
        setUpVerify();
        NotifyHours mockNotifyHours = mock(NotifyHours.class);
        when(mockNotifyHours.getNotifyFrom()).thenReturn(LocalTime.of(9,0));
        when(mockNotifyHours.getNotifyTo()).thenReturn(LocalTime.of(17,0));
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(NotifyHoursRepository.class)).thenReturn(mockNotifyHoursRepository);

            when(mockNotifyHoursRepository.findByPatientAndTutor(mockPatient, mockTutor)).thenReturn(mockNotifyHours);
            when(mockEvent.getTime()).thenReturn(LocalTime.of(10,0));

            Boolean result = Verify.VerifyHoursOfNotification(mockEvent, mockTutor, mockPatient);

            assertTrue(result);
        }
    }

    @Test
    public void testVerifyHoursOutOfRange(){
        setUpVerify();
        NotifyHours mockNotifyHours = mock(NotifyHours.class);
        when(mockNotifyHours.getNotifyFrom()).thenReturn(LocalTime.of(9,0));
        when(mockNotifyHours.getNotifyTo()).thenReturn(LocalTime.of(17,0));
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(NotifyHoursRepository.class)).thenReturn(mockNotifyHoursRepository);

            when(mockNotifyHoursRepository.findByPatientAndTutor(mockPatient, mockTutor)).thenReturn(mockNotifyHours);
            when(mockEvent.getTime()).thenReturn(LocalTime.of(8,0));

            Boolean result = Verify.VerifyHoursOfNotification(mockEvent, mockTutor, mockPatient);

            assertFalse(result);
        }
    }

    @Test
    public void testVerifyHoursNull(){
        setUpVerify();
        NotifyHours mockNotifyHours = mock(NotifyHours.class);
        when(mockNotifyHours.getNotifyFrom()).thenReturn(LocalTime.of(9,0));
        when(mockNotifyHours.getNotifyTo()).thenReturn(LocalTime.of(17,0));

        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(NotifyHoursRepository.class)).thenReturn(mockNotifyHoursRepository);

            when(mockNotifyHoursRepository.findByPatientAndTutor(mockPatient, mockTutor)).thenReturn(null);

            Boolean result = Verify.VerifyHoursOfNotification(mockEvent, mockTutor, mockPatient);

            assertNull(result);
        }
    }

    @Test
    public void testIsTutorOf(){
        setUpResearch();
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(TutorOfRepository.class)).thenReturn(mockTutorOfRepository);
            mockedSpringContext.when(() -> SpringContext.getBean(SharedWithRepository.class)).thenReturn(mockSharedWithRepository);

            when(mockTutorOfRepository.findAll()).thenReturn(asList(mockTutorOf));

            when(mockTutorOf.getTutor()).thenReturn(1);
            when(mockTutorOf.getPatient()).thenReturn(2);
            when(mockPatient.getId()).thenReturn(2);
            when(mockTutor.getId()).thenReturn(1);

            Map<String, Boolean> response = new HashMap<>();
            response.put("Crisis", true);
            response.put("Medication", true);
            response.put("Profile", true);

            Map<String, Boolean> methodResponse = Research.ResearchPermissions(mockPatient, mockTutor);

            assertEquals(response, methodResponse);

        }
    }

    @Test
    public void testIsSharedWith(){
        setUpResearch();
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(TutorOfRepository.class)).thenReturn(mockTutorOfRepository);
            mockedSpringContext.when(() -> SpringContext.getBean(SharedWithRepository.class)).thenReturn(mockSharedWithRepository);

            when(mockTutorOfRepository.findAll()).thenReturn(asList());
            when(mockSharedWithRepository.findAll()).thenReturn(asList(mockSharedWith));


            when(mockSharedWith.getTutorReceiving()).thenReturn(1);
            when(mockSharedWith.getPatient()).thenReturn(2);
            when(mockPatient.getId()).thenReturn(2);
            when(mockTutor.getId()).thenReturn(1);
            when(mockSharedWith.getMedicinePermision()).thenReturn(false);
            when(mockSharedWith.getRegisterCrisisPermision()).thenReturn(true);
            when(mockSharedWith.getProfilePermision()).thenReturn(false);

            Map<String, Boolean> response = new HashMap<>();
            response.put("Crisis", true);
            response.put("Medication", false);
            response.put("Profile", false);

            Map<String, Boolean> methodResponse = Research.ResearchPermissions(mockPatient, mockTutor);

            assertEquals(response, methodResponse);

        }
    }

    @Test
    public void testResearchPermissionsReturnEmpty(){
        setUpResearch();
        try(MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)){
            mockedSpringContext.when(() -> SpringContext.getBean(TutorOfRepository.class)).thenReturn(mockTutorOfRepository);
            mockedSpringContext.when(() -> SpringContext.getBean(SharedWithRepository.class)).thenReturn(mockSharedWithRepository);

            when(mockTutorOfRepository.findAll()).thenReturn(asList());
            when(mockSharedWithRepository.findAll()).thenReturn(asList());

            Map<String, Boolean> response = new HashMap<>();

            Map<String, Boolean> methodResponse = Research.ResearchPermissions(mockPatient, mockTutor);

            assertEquals(response, methodResponse);

        }
    }
}
