package pl.put.swolarz.service.dto;

import lombok.Data;
import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.exceptions.FHIRException;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PatientDetailsDto extends PatientDto {

    private String htmlDescription;
    private AddressDto address;
    private List<ObservationDto> observations;
    private List<MedicationRequestDto> medicationRequests;


    public PatientDetailsDto(Patient patient, List<Observation> patientObservations, List<MedicationRequest> medicationRequests) {
        super(patient);

        this.htmlDescription = patient.getText().getDivAsString();
        this.address = new AddressDto(patient.getAddress().get(0));
        this.observations = patientObservations.stream().map(ObservationDto::new).collect(Collectors.toList());
        this.medicationRequests = medicationRequests.stream().map(MedicationRequestDto::new).collect(Collectors.toList());
    }

    public void updatePatient(Patient patient) {
        // Todo update patient entity
    }
}
