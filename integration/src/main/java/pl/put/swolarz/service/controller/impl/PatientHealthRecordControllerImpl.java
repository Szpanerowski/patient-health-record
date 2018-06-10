package pl.put.swolarz.service.controller.impl;

import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.swolarz.client.FhirServiceClient;
import pl.put.swolarz.service.controller.PatientHealthRecordController;
import pl.put.swolarz.service.dto.PatientDetailsDto;
import pl.put.swolarz.service.dto.PatientDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/health")
public class PatientHealthRecordControllerImpl implements PatientHealthRecordController {

    @Autowired
    private FhirServiceClient fhirServiceClient;


    @Override
    @ResponseBody
    @RequestMapping(path="/patients", method = RequestMethod.GET)
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        try {
            return ResponseEntity.ok(
                    fhirServiceClient.getAllPatients().stream().map(PatientDto::new).collect(Collectors.toList())
            );

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @RequestMapping(path = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<PatientDetailsDto> getPatientDetails(@PathVariable("id") String patientId) {

        try {
            Patient patient = fhirServiceClient.getPatientById(patientId);

            if (patient == null)
                return ResponseEntity.notFound().build();

            List<Observation> patientObservations = fhirServiceClient.getObservationsForPatient(patientId);
            List<MedicationRequest> patientMedicationRequests = fhirServiceClient.getMedicationRequestsForPatient(patientId);

            PatientDetailsDto patientDetails = new PatientDetailsDto(patient, patientObservations, patientMedicationRequests);

            return ResponseEntity.ok(patientDetails);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
