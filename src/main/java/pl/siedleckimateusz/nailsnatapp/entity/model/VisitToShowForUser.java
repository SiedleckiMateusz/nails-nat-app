package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.VisitStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class VisitToShowForUser {

    private Long id;
    private LocalDate dateOfVisit;
    private LocalTime startTime;
    private VisitStatus status;
    private String moreInfo;
    private String treatmentName;

}
