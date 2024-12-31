package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.models.Tutor;
import com.example.episafezone.models.TutorOf;
import com.example.episafezone.repositories.SharedWithRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SharedWithServiceTest {
    @Mock
    SharedWithRepository sharedWithRepository;

    @Mock
    TutorOfService tutorOfService;

    @Mock
    TutorService tutorService;

    @InjectMocks
    SharedWithService sharedWithService;

    @Test
    public void findAllTest(){
        SharedWith sharedWith1 = new SharedWith();
        SharedWith sharedWith2 = new SharedWith();

        when(sharedWithRepository.findAll()).thenReturn(Arrays.asList(sharedWith1, sharedWith2));

        List<SharedWith> response = sharedWithService.findAll();

        assertEquals(2, response.size());
        assertTrue(response.contains(sharedWith1));
        assertTrue(response.contains(sharedWith2));
    }

    @Test
    public void findByTutorReceivingTest(){
        SharedWith sharedWith1 = new SharedWith(1,2,3, true, false, false, false);
        SharedWith sharedWith2 = new SharedWith(2,2,4, true, false, false, false);
        SharedWith sharedWith3 = new SharedWith(1,3,4, true, false, false, false);

        when(sharedWithRepository.findByTutorReceiving(2)).thenReturn(Arrays.asList(sharedWith1, sharedWith2));

        List<SharedWith> response = sharedWithService.findByTutorReceiving(2);

        assertEquals(2, response.size());
        assertTrue(response.contains(sharedWith1));
        assertTrue(response.contains(sharedWith2));
        assertFalse(response.contains(sharedWith3));
    }

    @Test
    public void findPatientByTutorReceivingTest(){
        SharedWith sharedWith1 = new SharedWith(1,2,3, true, false, false, false);
        SharedWith sharedWith2 = new SharedWith(2,2,4, true, false, false, false);
        SharedWith sharedWith3 = new SharedWith(1,3,5, true, false, false, false);

        when(sharedWithRepository.findByTutorReceiving(2)).thenReturn(Arrays.asList(sharedWith1, sharedWith2));

        List<Integer> result = sharedWithService.findPByTReceiving(2);

        assertEquals(2, result.size());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertFalse(result.contains(5));
    }

    @Test
    public void findPatientTutorsTest(){
        Tutor tutor1 = mock(Tutor.class);
        Tutor tutor2 = mock(Tutor.class);
        Tutor tutor3 = mock(Tutor.class);
        Tutor tutor4 = mock(Tutor.class);
        Tutor tutor5 = mock(Tutor.class);
        TutorOf tutorOf = new TutorOf(1,3,true);
        TutorOf tutorOf2 = new TutorOf(2,3,false);
        TutorOf tutorOf3 = new TutorOf(3,4,false);
        SharedWith sharedWith1 = new SharedWith(1,4,3, true, false, false, false);
        SharedWith sharedWith2 = new SharedWith(2,5,4, true, false, false, false);

        when(sharedWithRepository.findByTutorReceiving(2)).thenReturn(Arrays.asList(sharedWith1, sharedWith2));
        when(tutorOfService.findAll()).thenReturn(Arrays.asList(tutorOf, tutorOf2, tutorOf3));
        when(tutorService.findById(1)).thenReturn(tutor1);
        when(tutorService.findById(2)).thenReturn(tutor2);
        when(tutorService.findById(3)).thenReturn(tutor3);
        when(tutorService.findById(4)).thenReturn(tutor4);
        when(tutorService.findById(5)).thenReturn(tutor5);

        List<Tutor> result = sharedWithService.findPatientTutors(3);

        assertEquals(3, result.size());
        assertTrue(result.contains(tutor1));
        assertTrue(result.contains(tutor2));
        assertFalse(result.contains(tutor3));
        assertFalse(result.contains(tutor5));
        assertTrue(result.contains(tutor4));
    }
}
