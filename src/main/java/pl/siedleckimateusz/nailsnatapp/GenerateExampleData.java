package pl.siedleckimateusz.nailsnatapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.*;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoCategoryModel;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentDto;
import pl.siedleckimateusz.nailsnatapp.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Component
public class GenerateExampleData implements CommandLineRunner {

    private final UserService userService;
    private final TreatmentService treatmentService;
    private final VisitService visitService;
    private final PhotoCategoryService photoCategoryService;
    private final TimeOffService timeOffService;

    public GenerateExampleData(UserService userService, TreatmentService treatmentService, VisitService visitService, PhotoCategoryService photoCategoryService, TimeOffService timeOffService) {
        this.userService = userService;
        this.treatmentService = treatmentService;
        this.visitService = visitService;
        this.photoCategoryService = photoCategoryService;
        this.timeOffService = timeOffService;
    }

    @Override
    public void run(String... args) throws Exception {
        generateUsers();
        generateTreatments();
        generateVisit();
        generatePhotoCategories();
        generateTimesOff();
    }

    private void generateUsers() {
        NewUser userMatt = NewUser.builder()
                .firstName("Mateusz")
                .lastName("Siedlecki")
                .email("siedleckimateus@gmail.com")
                .phoneNumber("722045780")
                .authority(Authority.ADMIN)
                .username("admin")
                .password("abcd")
                .build();

        NewUser userNat = NewUser.builder()
                .firstName("Natalia")
                .lastName("Siedlecka")
                .email("siedleckaNat@gmail.com")
                .phoneNumber("726369636")
                .authority(Authority.EMPLOYEE)
                .username("employee")
                .password("abcd")
                .build();

        NewUser userAnn= NewUser.builder()
                .firstName("Anna")
                .lastName("Rybak")
                .email("666rybka666@gmail.com")
                .phoneNumber("726369636")
                .authority(Authority.CLIENT)
                .username("annarybak")
                .extraTime(15)
                .password("abcd")
                .build();

        NewUser userKarol= NewUser.builder()
                .firstName("Karolina")
                .lastName("Panasiuk")
                .email("panasiukkarolina@gmail.com")
                .phoneNumber("754243243")
                .authority(Authority.CLIENT)
                .username("panasiukkarolina")
                .extraTime(20)
                .password("abcd")
                .build();

        userService.save(userKarol);
        userService.save(userMatt);
        userService.save(userNat);
        userService.save(userAnn);
    }

    private void generateTreatments() {

        treatmentService.save(TreatmentDto.builder()
                .name("Manicure")
                .time(100)
                .price(60)
                .build());

        treatmentService.save(TreatmentDto.builder()
                .name("Depilacja")
                .price(100)
                .time(130)
                .build());

        treatmentService.save(TreatmentDto.builder()
                .name("Henna")
                .price(70)
                .time(70)
                .build());

        treatmentService.save(TreatmentDto.builder()
                .name("Pedicure")
                .time(90)
                .price(80)
                .build());

        treatmentService.save(TreatmentDto.builder()
                .name("IBX System")
                .time(45)
                .price(45)
                .build());

    }

    private void generateVisit(){
        List<TreatmentEntity> allTreatment = treatmentService.findAll();
        List<UserEntity> allUsers = userService.findAll();

        visitService.save(VisitEntity.builder()
                .dateOfVisit(LocalDate.of(2020,10,4))
                .startTime(LocalTime.of(12,30))
                .user(allUsers.get(1))
                .treatment(allTreatment.get(1))
                .moreInfo("Mam nadzieję że będzie ok")
                .build());


        visitService.save(VisitEntity.builder()
                .dateOfVisit(LocalDate.of(2020,10,4))
                .startTime(LocalTime.of(13,30))
                .user(allUsers.get(3))
                .treatment(allTreatment.get(2))
                .build());

        VisitEntity visit1 = VisitEntity.builder()
                .dateOfVisit(LocalDate.of(2020,9,12))
                .startTime(LocalTime.of(17,30))
                .user(allUsers.get(3))
                .treatment(allTreatment.get(1))
                .build();
        visit1.setStatus(VisitStatus.CANCELED);

        visitService.save(visit1);

        VisitEntity visit = VisitEntity.builder()
                .dateOfVisit(LocalDate.of(2020, 10, 7))
                .startTime(LocalTime.of(17, 30))
                .user(allUsers.get(3))
                .treatment(allTreatment.get(4))
                .build();
        visit.setStatus(VisitStatus.CONFIRMED);

        visitService.save(visit);

        VisitEntity visit2 = VisitEntity.builder()
                .dateOfVisit(LocalDate.of(2020, 10, 13))
                .startTime(LocalTime.of(12, 30))
                .user(allUsers.get(3))
                .treatment(allTreatment.get(1))
                .build();
        visit2.setStatus(VisitStatus.TO_CONFIRM_BY_USER);

        visitService.save(visit2);
    }

    private void generatePhotoCategories() {
        photoCategoryService.save(PhotoCategoryModel.builder().name("Jeden kolor").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Kilka kolorów").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Pyłek/brokat").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Własny rysunek").build());
    }

    private void generateTimesOff() {
        TimeOffEntity timeOff1 = timeOffService.save(TimeOffEntity.builder()
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(16, 59))
                .dayOfEvent(LocalDate.of(2020,9,14))
                .repeat(Repeat.WEEKLY)
                .build());

        TimeOffEntity timeOff2 = timeOffService.save(TimeOffEntity.builder()
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(16, 59))
                .dayOfEvent(LocalDate.of(2020,9,15))
                .repeat(Repeat.WEEKLY)
                .build());

        TimeOffEntity timeOff3 = timeOffService.save(TimeOffEntity.builder()
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(16, 59))
                .dayOfEvent(LocalDate.of(2020,9,16))
                .repeat(Repeat.WEEKLY)
                .build());

        TimeOffEntity timeOff4 = timeOffService.save(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020,10,13))
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(20, 0))
                .repeat(Repeat.NEVER)
                .build());

        TimeOffEntity timeOff5 = timeOffService.save(TimeOffEntity.builder()
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(21, 0))
                .dayOfEvent(LocalDate.of(2020,9,5))
                .repeat(Repeat.WEEKLY)
                .expirationDate(LocalDate.of(2020,12, Month.DECEMBER.length(Year.of(2020).isLeap())))
                .build());

        timeOffService.save(TimeOffEntity.builder()
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(14, 0))
                .dayOfEvent(LocalDate.of(2020,10,5))
                .repeat(Repeat.DAILY)
                .expirationDate(LocalDate.of(2020,12, Month.DECEMBER.length(Year.of(2020).isLeap())))
                .build());


    }
}
