package pl.siedleckimateusz.nailsnatapp.entity.validator;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.TreatmentMapper;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.VisitMapper;
import pl.siedleckimateusz.nailsnatapp.exception.VisitFormValidationException;
import pl.siedleckimateusz.nailsnatapp.repo.TimeOffRepoForTest;
import pl.siedleckimateusz.nailsnatapp.repo.TreatmentRepoForTest;
import pl.siedleckimateusz.nailsnatapp.repo.VisitRepoForTest;
import pl.siedleckimateusz.nailsnatapp.service.TimeOffService;
import pl.siedleckimateusz.nailsnatapp.service.TreatmentService;
import pl.siedleckimateusz.nailsnatapp.service.VisitService;
import pl.siedleckimateusz.nailsnatapp.time.VisitTimeManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


class VisitFormValidatorTest {

    private VisitFormValidator visitFormValidator;
    private VisitTimeManager visitTimeManager;
    private TreatmentService treatmentService;
    private TimeOffService timeOffService;
    private VisitService visitService;

    @BeforeEach
    void setUp() {
        visitService = new VisitService(VisitRepoForTest.getInstance(),new VisitMapper());
        timeOffService = new TimeOffService(TimeOffRepoForTest.getInstance());
        treatmentService = new TreatmentService(TreatmentRepoForTest.getInstance(),new TreatmentMapper());
        visitTimeManager = new VisitTimeManager(timeOffService,visitService);
        visitFormValidator = new VisitFormValidator(treatmentService,visitTimeManager);

    }


    @Test()
    void should_validate_treatment_and_throw_exception(){
//        when
        assertThrows(VisitFormValidationException.class,()->visitFormValidator.validateTreatment(6L));
    }

    @Test
    void should_validate_treatment_and_return_treatment() throws VisitFormValidationException {
        //when
        TreatmentEntity treatmentEntity = visitFormValidator.validateTreatment(1L);
        //then
        assertNotNull(treatmentEntity);
    }


    @Test
    void should_validate_date_and_return_date() throws VisitFormValidationException {
        //given
        LocalDate date = LocalDate.now();
        date = date.plusDays(2);
        String dateString = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        //when
        LocalDate validateDate = visitFormValidator.validateDate(dateString, 30);

        //then
        assertNotNull(validateDate);
    }

    @Test
    void should_validate_date_and_throw_exception(){
        //then
        assertThrows(VisitFormValidationException.class,()->visitFormValidator.validateDate(null,50));
    }

}