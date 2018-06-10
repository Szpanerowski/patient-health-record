package pl.put.swolarz.client.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.IParam;
import ca.uhn.fhir.rest.gclient.IQuery;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.instance.model.api.IBaseBundle;
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
        context.getRestfulClientFactory().setSocketTimeout(5 * 60 * 1000);

        client = context.newRestfulGenericClient(FHIR_SERVER_BASE);
    }

    @Override
    public List<Patient> getAllPatients() {

        IParam orderBy = Patient.NAME;

        return getAll(Patient.class, null, orderBy, true);
    }

    @Override
    public Patient getPatientById(String patientId) {
        return get(Patient.class, patientId);
    }

    @Override
    public List<Observation> getObservationsForPatient(String patientId) {

        ICriterion where = Observation.PATIENT.hasId(patientId);
        IParam orderBy = Observation.DATE;

        return getAll(Observation.class, where, orderBy, false);
    }

    @Override
    public List<MedicationRequest> getMedicationRequestsForPatient(String patientId) {

        ICriterion where = MedicationRequest.PATIENT.hasId(patientId);
        IParam orderBy = MedicationRequest.AUTHOREDON;

        return getAll(MedicationRequest.class, where, orderBy, false);
    }

    private <T extends IBaseResource> List<T> getAll(Class<T> resource, ICriterion criterion, IParam orderBy, boolean ascending) {

        try{
            Bundle result = fetchSearchResult(resource, criterion, orderBy, ascending);

            return (List<T>) loadPages(result);

        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<? extends Resource> loadPages(Bundle result) {

        List<Resource> resources = new ArrayList<>();
        resources.addAll(result.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).collect(Collectors.toList()));

        while (result.getLink(Bundle.LINK_NEXT) != null) {

            result = client.loadPage().next(result).execute();
            resources.addAll(result.getEntry().stream().map(Bundle.BundleEntryComponent::getResource).collect(Collectors.toList()));
        }

        return resources;
    }

    private <T extends IBaseResource> Bundle fetchSearchResult(Class<T> resource, ICriterion criterion, IParam orderBy, boolean ascending) {

        IQuery<IBaseBundle> query = client.search().forResource(resource);

        if (criterion != null)
            query.where(criterion);

        if (orderBy != null) {

            if (ascending)
                query.sort().ascending(orderBy);
            else
                query.sort().descending(orderBy);
        }

        return query.returnBundle(Bundle.class).execute();
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
