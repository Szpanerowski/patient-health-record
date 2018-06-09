package pl.put.swolarz.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.swolarz.service.dto.PatientDetailsDto;
import pl.put.swolarz.service.dto.PatientDto;

import java.util.List;

@RestController
@RequestMapping("/health")
public interface PatientHealthRecordController {

    @ResponseBody
    @RequestMapping(path="/patients", method = RequestMethod.GET)
    List<PatientDto> getAllPatients();

    @RequestMapping(path = "/patient/{id}", method = RequestMethod.GET)
    ResponseEntity<PatientDetailsDto> getPatientDetails(@PathVariable("id") String id);
}
