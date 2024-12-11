package com.example.episafezone.services;

import com.example.episafezone.DTO.PatientsDTO.PatientCrisisDTO;
import com.example.episafezone.config.SpringContext;
import com.example.episafezone.events.CrisisEvent;
import com.example.episafezone.events.Event;
import com.example.episafezone.events.EventFactory;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.Crisis;
import com.example.episafezone.models.Patient;
import com.example.episafezone.repositories.CrisisRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CrisisService implements CrisisServiceInterface {
    @Autowired
    CrisisRespository crisisRepo;

    private final CrisisRespository repo;

    public CrisisService(CrisisRespository repo) {
        this.repo = repo;
    }

    public List<Crisis> getAll() {
        return repo.findAll();
    }

    public Crisis getById(Integer id) {
        Optional<Crisis> crisisOptional = repo.findById(id);
        if (crisisOptional.isPresent()) {
            return crisisOptional.get();
        } else {
            throw new ResourceNotFoudException("No se ha encontrado la crisis asociada a este id" + id);
        }
    }

    public List<Crisis> getByPatient(Integer id) {
        return repo.findByPatient(id);
    }

    public List<Crisis> getByMonth(List<Crisis> crisisList, int year, int month) {
        List<Crisis> filteredList = new ArrayList<>();

        for (Crisis crisis : crisisList) {
            Date date = crisis.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int crisisYear = calendar.get(Calendar.YEAR);
            int crisisMonth = calendar.get(Calendar.MONTH) + 1;

            if (crisisYear == year && crisisMonth == month) {
                filteredList.add(crisis);
            }
        }
        filteredList.sort(Comparator.comparing(Crisis::getDate));
        return filteredList;
    }

    public Crisis createCrisis(Crisis crisis) {
        PatientService patientService = SpringContext.getBean(PatientService.class);
        Patient patient = patientService.findById(crisis.getPatient());
        Event event = EventFactory.createCrisisEvent("registered");
        patient.triggerEvent(event);
        return repo.save(crisis);
    }

    public void applyMedication(Integer patientId) {
        PatientService patientService = SpringContext.getBean(PatientService.class);
        Patient patient = patientService.findById(patientId);
        Event event = EventFactory.createMedicationEvent("taken");
        patient.triggerEvent(event);
    }

    public List<PatientCrisisDTO> lastWeekCrisis(Integer patientId){
        List<Crisis> crisisList = crisisRepo.findByPatient(patientId);
        LocalDate hoy = LocalDate.now();
        Map<LocalDate, Long> crisisPorDia = crisisList.stream()
                .map(crisis -> convertirDateToLocalDate(crisis.getDate()))
                .filter(Objects::nonNull)
                .filter(fecha -> !fecha.isBefore(hoy.minusDays(7)) && !fecha.isAfter(hoy))
                .collect(Collectors.groupingBy(fecha -> fecha, Collectors.counting()));

        // Crear una lista de PatientCrisisDTO con los días de los últimos 7 días
        return IntStream.rangeClosed(0, 6)
                .mapToObj(diasAtras -> hoy.minusDays(diasAtras)) 
                .sorted()
                .map(fecha -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date fechaDate = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    String fechaFormateada = sdf.format(fechaDate);
                    return new PatientCrisisDTO(fechaFormateada, crisisPorDia.getOrDefault(fecha, 0L).intValue());
                })
                .collect(Collectors.toList());
    }
    private static LocalDate convertirDateToLocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static java.sql.Date convertirLocalDateToSqlDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return java.sql.Date.valueOf(date);
    }

}
