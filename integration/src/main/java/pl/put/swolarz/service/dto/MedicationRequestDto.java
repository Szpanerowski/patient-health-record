package pl.put.swolarz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hl7.fhir.dstu3.model.MedicationRequest;
import org.hl7.fhir.exceptions.FHIRException;

import java.util.Date;

public class MedicationRequestDto {

    private String id;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date time;

    public MedicationRequestDto(MedicationRequest medicationRequest) {

        this.id = medicationRequest.getId();
        medicationRequest.getNote();

        try {
            this.description = medicationRequest.getMedicationCodeableConcept().getText();
        } catch (FHIRException e) {
            this.description = "# Description not available...";
        }

        this.time = medicationRequest.getAuthoredOn();
    }
}
