package pl.put.swolarz.client.constraint;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PeriodConstraint {

    private Date dateFrom;
    private Date dateTo;
}
