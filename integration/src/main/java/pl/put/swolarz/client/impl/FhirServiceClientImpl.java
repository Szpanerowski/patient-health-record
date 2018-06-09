package pl.put.swolarz.client.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.stereotype.Service;
import pl.put.swolarz.client.FhirServiceClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FhirServiceClientImpl implements FhirServiceClient {

    private static final String FHIR_SERVER_BASE = "http://localhost:8080/baseDstu3/";

    private FhirContext context;
    private IGenericClient client;

    @PostConstruct
    public void init() {
        context = FhirContext.forDstu3();
        context.getRestfulClientFactory().setSocketTimeout(5 * 60 * 1000); // 5-minute timeout

        client = context.newRestfulGenericClient(FHIR_SERVER_BASE);
    }

    @Override
    public List<Patient> getAllPatients() {
        return null;
    }

    @Override
    public Patient getPatientById() {
        return null;
    }

    @Override
    public List<Observation> getObservationsForPatient(String patientId) {
        return null;
    }

    @Override
    public List<MedicationRequest> getMedicationRequestsForPatient(String patientId) {
        return null;
    }

    private <T extends IBaseResource> List<T> getAll(Class<T> resource) {

        try{
            Bundle result = client.search().forResource(resource).returnBundle(Bundle.class).execute();
            List<T> entries = (List<T>) result.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).collect(Collectors.toList());

            return entries;

        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T extends IBaseResource> T get(Class<T> resource, String id) {

        try {
            return client.read().resource(resource).withId(id).execute();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
