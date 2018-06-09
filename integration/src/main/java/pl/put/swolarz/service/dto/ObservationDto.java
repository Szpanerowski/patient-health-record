package pl.put.swolarz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.exceptions.FHIRException;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class ObservationDto {

    private String id;
    private String description;
    private String code;
    private BigDecimal value;
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date time;


    public ObservationDto(Observation observation) {

        this.id = observation.getId();
        this.description = observation.getCode().getText();

        try{
            this.code = observation.getValueQuantity().getCode();
            this.value = observation.getValueQuantity().getValue();

        } catch (FHIRException e) {

            this.code = null;
            this.value = null;
        }

        this.comment = observation.getComment();
        this.time = observation.getIssued();
    }
}
