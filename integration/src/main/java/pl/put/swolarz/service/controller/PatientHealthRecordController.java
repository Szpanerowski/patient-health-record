package pl.put.swolarz.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.swolarz.service.dto.PatientDetailsDto;
import pl.put.swolarz.service.dto.PatientDto;

import java.util.List;

public interface PatientHealthRecordController {

    ResponseEntity<List<PatientDto>> getAllPatients();
    ResponseEntity<PatientDetailsDto> getPatientDetails(String id);
}
