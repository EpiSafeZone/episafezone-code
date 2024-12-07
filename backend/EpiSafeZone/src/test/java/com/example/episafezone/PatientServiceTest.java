package com.example.episafezone;

import com.example.episafezone.config.SpringContext;
import com.example.episafezone.models.Patient;
import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.SharedWithRepository;
import com.example.episafezone.services.PatientService;
import com.example.episafezone.services.SharedWithService;
import com.example.episafezone.services.TutorOfService;
import com.example.episafezone.services.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class PatientServiceTest {
    @Mock
    private SharedWithRepository sharedWithRepo;

    @Mock
    private TutorOfService tutorOfService;

    @Mock
    private TutorService tutorService;

    @InjectMocks
    private SharedWithService sharedWithService; // Inyecta automáticamente mocks excepto tutorService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
        @Test
    void testFindPatientTutors() {
            try (MockedStatic<SpringContext> mockedSpringContext = Mockito.mockStatic(SpringContext.class)) {
                mockedSpringContext.when(() -> SpringContext.getBean(SharedWithRepository.class)).thenReturn(sharedWithRepo);
                mockedSpringContext.when(() -> SpringContext.getBean(TutorOfService.class)).thenReturn(tutorOfService);
                mockedSpringContext.when(() -> SpringContext.getBean(TutorService.class)).thenReturn(tutorService);

                //Paciente
                Patient patient = new Patient();
                patient.setId(1);

                //Tutores
                Tutor tutor = new Tutor();
                tutor.setId(2);
                Tutor tutor2 = new Tutor();
                tutor2.setId(3);
                Tutor tutor3 = new Tutor();
                tutor3.setId(4);
                Tutor tutor4 = new Tutor();
                tutor4.setId(5);
                Tutor tutor5 = new Tutor();
                tutor5.setId(6);

                //Asignación de tutores a los pacientes que son los padres
                TutorOf tutorOf = new TutorOf(tutor.getId(), patient.getId(), true);
                TutorOf tutorOf2 = new TutorOf(tutor2.getId(), patient.getId(), false);

                when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf, tutorOf2));
                when(tutorService.findAll()).thenReturn(Arrays.asList(tutor, tutor2, tutor3, tutor4, tutor5));
                when(tutorService.findById(2)).thenReturn(tutor);
                when(tutorService.findById(3)).thenReturn(tutor2);
                when(tutorService.findById(4)).thenReturn(tutor3);
                when(tutorService.findById(5)).thenReturn(tutor4);
                when(tutorService.findById(6)).thenReturn(tutor5);

                //Se Comparte el paciente con dos tutores
                SharedWith sharedWith = new SharedWith(tutor.getId(), tutor3.getId(), patient.getId(), null, null, null, null);
                SharedWith sharedWith2 = new SharedWith(tutor.getId(), tutor4.getId(), patient.getId(), null, null, null, null);

                when(sharedWithRepo.findAll()).thenReturn(Arrays.asList(sharedWith, sharedWith2));

                List<Tutor> result = sharedWithService.findPatientTutors(patient.getId());

                assertEquals(4, result.size());
                assertTrue(result.contains(tutor));
                assertTrue(result.contains(tutor2));
                assertTrue(result.contains(tutor3));
                assertTrue(result.contains(tutor4));
                assertFalse(result.contains(tutor5));

                verify(sharedWithRepo, times(1)).findAll();
                verify(tutorOfService, times(1)).findAll();
                verify(tutorService, times(1)).findById(2);
                verify(tutorService, times(1)).findById(3);
                verify(tutorService, times(1)).findById(4);
                verify(tutorService, times(1)).findById(5);
            }

    }
}
