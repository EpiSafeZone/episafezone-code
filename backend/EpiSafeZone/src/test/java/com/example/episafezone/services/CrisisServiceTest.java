package com.example.episafezone.services;

import com.example.episafezone.DTO.PatientsDTO.PatientCrisisDTO;
import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.exceptions.AttributeMissingException;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Crisis;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.CrisisRespository;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrisisServiceTest {
    @Mock
    private CrisisRespository crisisRepo;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private CrisisService crisisService;

    @Test
    public void getAllTest(){
        //Definición de las crisis que vamos a tener
        Crisis crisis1 = new Crisis();
        Crisis crisis2 = new Crisis();
        Crisis crisis3 = new Crisis();

        when(crisisRepo.findAll()).thenReturn(Arrays.asList(crisis1, crisis2, crisis3));

        List<Crisis> crisisList = crisisService.getAll();

        assertEquals(3  , crisisList.size());
        assertTrue(crisisList.contains(crisis1));
        assertTrue(crisisList.contains(crisis2));
        assertTrue(crisisList.contains(crisis3));
    }

    @Test
    public void getbyIdTest(){
        Crisis crisis1 = new Crisis();
        crisis1.setId(1);

        when(crisisRepo.findById(1)).thenReturn(Optional.of(crisis1));

        Crisis result = crisisService.getById(1);

        assertNotNull(result);
        assertEquals(crisis1, result);
        Mockito.verify(crisisRepo, Mockito.times(1)).findById(1);
    }

    @Test
    public void getByIdTestThrowsResourceNotFoundException(){
        when(crisisRepo.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoudException.class, () -> crisisService.getById(99));
        verify(crisisRepo, Mockito.times(1)).findById(99);
    }

    @Test
    public void getByPatientTest(){
        Crisis crisis1 = new Crisis();
        crisis1.setPatient(1);
        Crisis crisis2 = new Crisis();
        crisis2.setPatient(2);
        Crisis crisis3 = new Crisis();
        crisis3.setPatient(1);

        when(crisisRepo.findByPatient(1)).thenReturn(Arrays.asList(crisis1, crisis3));

        List<Crisis> result = crisisService.getByPatient(1);

        assertEquals(2, result.size());
        assertTrue(result.contains(crisis1));
        assertTrue(result.contains(crisis3));
        assertFalse(result.contains(crisis2));
        verify( crisisRepo, Mockito.times(1)).findByPatient(1);
    }

    @Test
    public void getByPatientReturnsEmptyList(){
        Crisis crisis1 = new Crisis();
        crisis1.setPatient(2);
        Crisis crisis2 = new Crisis();
        crisis2.setPatient(2);

        when(crisisRepo.findByPatient(1)).thenReturn(Arrays.asList());

        List<Crisis> result = crisisService.getByPatient(1);
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
        verify( crisisRepo, Mockito.times(1)).findByPatient(1);
    }

    @Test
    public void getByMonthReturnsEmptyList(){
        List<Crisis> crisisList = Collections.emptyList();
        List<Crisis> result = crisisService.getByMonth(crisisList, 2024, 10);

        assertTrue(result.isEmpty());
    }

    @Test
    public void getByMonthNoMatches(){
        Crisis crisis1 = new Crisis(100, new Date(2023 - 1900, 1-1, 15), "15:00", "Mirando el movil",
                false, 1, 1);
        Crisis crisis2 = new Crisis(150, new Date(2023 - 1900, 2-1, 21), "16:00", "Jugando en el patio",
                false, 2, 2);
        List<Crisis>  crisisList = Arrays.asList(crisis1, crisis2);

        List<Crisis> result = crisisService.getByMonth(crisisList, 2023, 10);

        assertTrue(result.isEmpty());
    }
    @Test
    public void getByMonthSomeMatches(){
        Crisis crisis1 = new Crisis(100, new Date(2023-1900, 1-1, 15), "15:00", "Mirando la tele",
                false, 1, 1);
        Crisis crisis2 = new Crisis(100, new Date(2023-1900, 1-1, 20), "16:00", "Jugando en el patio",
                false, 1, 1);
        Crisis crisis3 = new Crisis(200, new Date(2023-1900, 2-1, 15), "17:00", "Mirando una pantalla",
                false, 1, 1);
        List<Crisis> crisisList = Arrays.asList(crisis1, crisis2, crisis3);

        List<Crisis> result = crisisService.getByMonth(crisisList, 2023, 1);

        assertEquals(2, result.size());
        assertTrue(result.contains(crisis1));
        assertTrue(result.contains(crisis2));
        assertFalse(result.contains(crisis3));
    }

    @Test
    public void getByMonthNullDates(){
        Crisis crisis1 = new Crisis(100, null, "15:00", "Mirando el movil", false, 1, 1);
        Crisis crisis2 = new Crisis(200, null, "17:00", "Jugando en el patio", false, 1, 1);

        List<Crisis> crisisList = Arrays.asList(crisis1, crisis2);

        List<Crisis> result = crisisService.getByMonth(crisisList, 2023, 1);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSaveValidCrisis_WithSpringContext() {

        Crisis crisis = new Crisis(30, new Date(), "10:00", "Test Context", true, 1, 1);
        Patient mockPatient = new Patient();
        Event mockEvent = mock(Event.class);

        // Mockear SpringContext.getBean()
        try (MockedStatic<SpringContext> mockedSpringContext = mockStatic(SpringContext.class)) {

            mockedSpringContext.when(() -> SpringContext.getBean(PatientService.class)).thenReturn(patientService);
            when(patientService.findById(1)).thenReturn(mockPatient);

            // Mockear EventFactory
            try (MockedStatic<EventFactory> mockedFactory = mockStatic(EventFactory.class)) {
                mockedFactory.when(() -> EventFactory.createCrisisEvent("registered")).thenReturn(mockEvent);


                Crisis savedCrisis = new Crisis(30, new Date(), "10:00", "Test Context", true, 1, 1);
                savedCrisis.setId(1);

                when(crisisRepo.save(crisis)).thenReturn(savedCrisis);

                Crisis result = crisisService.createCrisis(crisis);

                // Verificar resultados
                assertNotNull(result);
                assertEquals(1, result.getId());
                assertEquals("Test Context", result.getContext());

                // Verificar interacciones
                verify(patientService, times(1)).findById(1);
                mockedFactory.verify(() -> EventFactory.createCrisisEvent("registered"), times(1));
                verify(crisisRepo, times(1)).save(crisis);
            }
        }
    }

    @Test
    public void testCreateCrisis_PatientNotFound(){
        Crisis crisis = new Crisis(30, new Date(), "10:00", "Test Context", true, 1, 99);
        Patient mockPatient = new Patient();
        Event mockEvent = mock(Event.class);

        try(MockedStatic<SpringContext> mockedSpringContext = mockStatic(SpringContext.class)) {

            mockedSpringContext.when(() -> SpringContext.getBean(PatientService.class)).thenReturn(patientService);
            when(patientService.findById(99)).thenThrow(new ResourceNotFoudException("Patient not foud"));
            try (MockedStatic<EventFactory> mockedFactory = mockStatic(EventFactory.class)) {
                mockedFactory.when(() -> EventFactory.createCrisisEvent("registered")).thenReturn(mockEvent);

                ResourceNotFoudException exception = assertThrows(ResourceNotFoudException.class, () ->crisisService.createCrisis(crisis));

                verify(patientService, times(1)).findById(99);
                mockedFactory.verify(() -> EventFactory.createCrisisEvent("registered"), never());
                verify(crisisRepo, never()).save(any());
            }

        }
    }

    @Test
    public void testCreateCrisis_ThrowsExceptionWhenPatientIsMissing(){
        Crisis crisis = new Crisis(30, new Date(), "10:00", "Test Context", true, 1, null);
        AttributeMissingException exception = assertThrows(AttributeMissingException.class, () ->crisisService.createCrisis(crisis));

        assertEquals("attributes missing: patient",exception.getMessage());
    }

    @Test
    public void testCreateCrisis_ThrowsExceptionWhenManifestationIsMissing(){
        Crisis crisis = new Crisis(30, new Date(), "10:00", "Test Context", true, null, 1);
        AttributeMissingException exception = assertThrows(AttributeMissingException.class, () ->crisisService.createCrisis(crisis));

        assertEquals("attributes missing: manifestation",exception.getMessage());
    }

    @Test
    public void testCreateCrisis_ThrowsExceptionWhenManifestationAndPatientIsMissing(){
        Crisis crisis = new Crisis(30, new Date(), "10:00", "Test Context", true, null, null);
        AttributeMissingException exception = assertThrows(AttributeMissingException.class, () ->crisisService.createCrisis(crisis));

        assertEquals("attributes missing: patient, manifestation",exception.getMessage());
    }

    @Test
    void testLastWeekCrisis_WithTwoCrisis() {

        List<Crisis> mockCrisisList = Arrays.asList(
                new Crisis(30, new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), "10:00", "Test1", true, 1, 1),
                new Crisis(30, new Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000), "15:00", "Test2", false, 1, 1)
        );

        when(crisisRepo.findByPatient(1)).thenReturn(mockCrisisList);


        List<PatientCrisisDTO> result = crisisService.lastWeekCrisis(1);


        assertNotNull(result);
        assertEquals(7, result.size());
        assertEquals(1, result.get(1).getNumCrisis());
        assertEquals(1, result.get(4).getNumCrisis());
        assertEquals(0, result.get(0).getNumCrisis()); 


        verify(crisisRepo, times(1)).findByPatient(1);
    }

    @Test
    public void testLastWeekCrisis_EmptyList() {

        when(crisisRepo.findByPatient(1)).thenReturn(Collections.emptyList());

        List<PatientCrisisDTO> result = crisisService.lastWeekCrisis(1);


        assertNotNull(result);
        assertEquals(7, result.size());
        for (PatientCrisisDTO dto : result) {
            assertEquals(0, dto.getNumCrisis());
        }


        verify(crisisRepo, times(1)).findByPatient(1);
    }

    @Test
    public void testLastWekkCrisis_NullPatientId(){
        List<PatientCrisisDTO> resutl = crisisService.lastWeekCrisis(null);

        assertNotNull(resutl);
        assertTrue(resutl.isEmpty());
        verify(crisisRepo, never()).findByPatient(1);
    }

}
