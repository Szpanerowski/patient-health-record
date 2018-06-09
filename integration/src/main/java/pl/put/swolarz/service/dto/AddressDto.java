package pl.put.swolarz.service.dto;

import lombok.Data;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.PrimitiveType;

import java.io.Serializable;
import java.util.stream.Collectors;

@Data
public class AddressDto implements Serializable {

    private String lines;
    private String postalCode;
    private String city;


    public AddressDto(Address address) {

        this.lines = String.join("\n",
                address.getLine().stream().map(PrimitiveType::getValue).collect(Collectors.toList()));
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
    }

    public void updateAddress(Address address) {
        // Todo update address
    }
}
