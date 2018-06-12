package pl.put.swolarz.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientDto extends ResourceDto {

    private String firstName;
    private String lastName;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    public PatientDto(Patient patient) {
        super(patient);

        List<HumanName> names = patient.getName();
        HumanName name = names.stream().filter(n -> n.getUse().equals(HumanName.NameUse.OFFICIAL)).findFirst().orElse(names.get(0));

        this.firstName = name.getGiven().get(0).getValue();
        this.lastName = name.getFamily();
        this.gender = patient.getGender().getDisplay();
        this.birthDate = patient.getBirthDate();
    }

    public void updatePatient(Patient patient) {
        // Todo update patient entity
    }
}
