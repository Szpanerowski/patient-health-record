package pl.put.swolarz.client;

import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;

import java.util.List;

public interface FhirServiceClient {

    List<Patient> getAllPatients();
    Patient getPatientById(String patientId);

    List<Observation> getObservationsForPatient(String patientId);
    List<MedicationRequest> getMedicationRequestsForPatient(String patientId);
}
