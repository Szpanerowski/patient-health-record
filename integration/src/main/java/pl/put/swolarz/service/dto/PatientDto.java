package pl.put.swolarz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PatientDto implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    public PatientDto(Patient patient) {

        List<HumanName> names = patient.getName();
        HumanName name = names.stream().filter(n -> n.getUse().equals(HumanName.NameUse.OFFICIAL)).findFirst().orElse(names.get(0));

        this.id = patient.getId();
        this.firstName = name.getGiven().get(0).getValue();
        this.lastName = name.getFamily();
        this.gender = patient.getGender().getDisplay();
        this.birthDate = patient.getBirthDate();

        correctNames();
    }

    private void correctNames() {

        firstName = firstName.replaceAll("[0-9]", "");
        lastName = lastName.replaceAll("[0-9]", "");
    }
}
