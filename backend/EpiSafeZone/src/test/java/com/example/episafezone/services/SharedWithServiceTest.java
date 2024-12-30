package com.example.episafezone.services;

import com.example.episafezone.models.SharedWith;
import com.example.episafezone.repositories.SharedWithRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void FindAllTest(){
        SharedWith sharedWith1 = new SharedWith();
        SharedWith sharedWith2 = new SharedWith();

        when(sharedWithRepository.findAll()).thenReturn(Arrays.asList(sharedWith1, sharedWith2));

        List<SharedWith> response = sharedWithService.findAll();

        assertEquals(2, response.size());
        assertTrue(response.contains(sharedWith1));
        assertTrue(response.contains(sharedWith2));
    }
}
