package pl.put.swolarz.service.controller.impl;

import org.springframework.http.ResponseEntity;
import pl.put.swolarz.service.controller.PatientHealthRecordController;
import pl.put.swolarz.service.dto.PatientDetailsDto;
import pl.put.swolarz.service.dto.PatientDto;

import java.util.List;

public class PatientHealthRecordControllerImpl implements PatientHealthRecordController {

    @Override
    public List<PatientDto> getAllPatients() {
        return null;
    }

    @Override
    public ResponseEntity<PatientDetailsDto> getPatientDetails(String id) {
        return null;
    }


}
