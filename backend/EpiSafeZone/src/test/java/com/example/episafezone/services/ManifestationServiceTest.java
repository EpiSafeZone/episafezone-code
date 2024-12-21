package com.example.episafezone.services;

import com.example.episafezone.models.HasManifestation;
import com.example.episafezone.models.Manifestation;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.ManifestationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManifestationServiceTest {

    @Mock
    HasManifestationService hasManifestationService;

    @Mock
    ManifestationRepository manifestationRepository;

    @Mock
    PatientService patientService;

    @InjectMocks
    ManifestationService manifestationService;

    @Test
    public void testGetManifestationFromPatientWhenPatientIdNull(){
        List<Manifestation> resposne = manifestationService.getManifestationFromPatient(null);

        assertTrue(resposne.isEmpty());
    }

    @Test
    public void testGetManifestationFromPatient(){

        Manifestation manifestation = new Manifestation("manifestacion1", "descripcion1");
        manifestation.setId(1);
        Manifestation manifestation2 = new Manifestation("manifestacion2", "descripcion2");
        manifestation2.setId(2);
        Manifestation manifestation3 = new Manifestation("manifestacion3", "descripcion3");
        manifestation3.setId(3);

        Patient patient = new Patient();
        patient.setId(1);

        HasManifestation hasManifestation = mock(HasManifestation.class);
        hasManifestation.setManifestation(1);
        hasManifestation.setPatient(1);
        HasManifestation hasManifestation2 = mock(HasManifestation.class);
        hasManifestation2.setManifestation(2);
        hasManifestation2.setPatient(1);

        when(hasManifestationService.patientHasManifestations(1)).thenReturn(Arrays.asList(hasManifestation, hasManifestation2));
        when(hasManifestation.getManifestation()).thenReturn(1);
        when(hasManifestation2.getManifestation()).thenReturn(2);
        when(manifestationRepository.findById(1)).thenReturn(Optional.of(manifestation));
        when(manifestationRepository.findById(2)).thenReturn(Optional.of(manifestation2));


        List<Manifestation> result = manifestationService.getManifestationFromPatient(patient.getId());

        assertEquals(2, result.size());
        assertTrue(result.contains(manifestation));
        assertTrue(result.contains(manifestation2));
        assertFalse(result.contains(manifestation3));
    }

}
