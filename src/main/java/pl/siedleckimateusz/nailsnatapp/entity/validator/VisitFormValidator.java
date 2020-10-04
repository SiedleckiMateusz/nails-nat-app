package pl.siedleckimateusz.nailsnatapp.entity.validator;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.exception.VisitErrorMessage;
import pl.siedleckimateusz.nailsnatapp.exception.VisitFormValidationException;
import pl.siedleckimateusz.nailsnatapp.service.TreatmentService;
import pl.siedleckimateusz.nailsnatapp.time.VisitTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class VisitFormValidator {

    private final TreatmentService treatmentService;
    private final VisitTimeManager visitTimeManager;

    public VisitFormValidator(TreatmentService treatmentService, VisitTimeManager visitTimeManager) {
        this.treatmentService = treatmentService;
        this.visitTimeManager = visitTimeManager;
    }

    public TreatmentEntity validateTreatment(Long id) throws VisitFormValidationException {
        if (id==null) throw new VisitFormValidationException(VisitErrorMessage.EMPTY_TREATMENT);

        Optional<TreatmentEntity> byId = treatmentService.findById(id);

        return byId.orElseThrow(()-> new VisitFormValidationException(VisitErrorMessage.TREATMENT_NOT_EXIST));

    }

    public LocalDate validateDate(String dateOfVisitString, int fullVisitTime) throws VisitFormValidationException {
        if (!textIsOk(dateOfVisitString)) throw new VisitFormValidationException(VisitErrorMessage.EMPTY_DATE);

        LocalDate dateOfVisit = LocalDate.parse(dateOfVisitString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (!visitTimeManager.isAvailableDateForVisit(dateOfVisit,fullVisitTime)) throw new VisitFormValidationException(VisitErrorMessage.NOT_AVAILABLE_DATE);

        return dateOfVisit;


    }

    public LocalDateTime validateTime(String dateTime, int visitTime) throws VisitFormValidationException {

        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalTime parsedStartTime = LocalTime.of(parsedDateTime.getHour(), parsedDateTime.getMinute());
        LocalDate parsedDate = LocalDate.of(parsedDateTime.getYear(), parsedDateTime.getMonth(), parsedDateTime.getDayOfMonth());

        if (!visitTimeManager.isAvailableTimeForVisit(parsedDate,parsedStartTime,visitTime))
            throw new VisitFormValidationException(VisitErrorMessage.NOT_AVAILABLE_DATE_AND_TIME);

        return parsedDateTime;
    }

    public boolean textIsOk(String text) {
        return (text != null && !text.isEmpty());
    }

}
