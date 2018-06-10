package pl.put.swolarz.service.dto;

import lombok.Data;
import org.hl7.fhir.dstu3.model.Resource;

import java.io.Serializable;

@Data
class ResourceDto implements Serializable {

    private String id;

    ResourceDto(Resource resource) {
        id = resource.getIdElement().getIdPart();
    }
}
