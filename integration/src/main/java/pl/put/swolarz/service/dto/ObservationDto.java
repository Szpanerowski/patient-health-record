package pl.put.swolarz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.exceptions.FHIRException;

import java.math.BigDecimal;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
public class ObservationDto extends ResourceDto {

    private String description;
    private String code;
    private BigDecimal value;
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date time;


    public ObservationDto(Observation observation) {
        super(observation);

        this.description = observation.getCode().getText();
        if (this.description == null)
            this.description = "Patient observation";

        try{
            this.code = observation.getCode().getCoding().get(0).getCode();
            this.value = observation.getValueQuantity().getValue();

        } catch (Exception e) {

            this.code = null;
            this.value = null;
        }

        this.comment = observation.getComment();
        this.time = observation.getIssued();
    }
}
