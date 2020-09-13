package pl.siedleckimateusz.nailsnatapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;
import pl.siedleckimateusz.nailsnatapp.entity.GroupTreatment;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentModel;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewUser;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoCategoryModel;
import pl.siedleckimateusz.nailsnatapp.service.PhotoCategoryService;
import pl.siedleckimateusz.nailsnatapp.service.TreatmentService;
import pl.siedleckimateusz.nailsnatapp.service.UserService;
import pl.siedleckimateusz.nailsnatapp.service.VisitService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GenerateExampleData implements CommandLineRunner {

    private final UserService userService;
    private final TreatmentService treatmentService;
    private final VisitService visitService;
    private final PhotoCategoryService photoCategoryService;

    public GenerateExampleData(UserService userService, TreatmentService treatmentService, VisitService visitService, PhotoCategoryService photoCategoryService) {
        this.userService = userService;
        this.treatmentService = treatmentService;
        this.visitService = visitService;
        this.photoCategoryService = photoCategoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        generateUsers();
        generateTreatments();
        generateVisit();
        generatePhotoCategories();
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
                .username("client")
                .password("abcd")
                .build();

        userService.save(userMatt);
        userService.save(userNat);
        userService.save(userAnn);
    }

    private void generateTreatments() {

        treatmentService.save(TreatmentModel.builder()
                .name("Ściąganie poprzedniej stylizacji")
                .group(GroupTreatment.TECHNICAL)
                .price(20)
                .time(30)
                .allFingers(true)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Obróbka skórek")
                .group(GroupTreatment.TECHNICAL)
                .price(15)
                .time(20)
                .allFingers(true)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Łatanie włóknami")
                .group(GroupTreatment.TECHNICAL)
                .price(5)
                .time(15)
                .allFingers(false)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Przedłużanie żelem")
                .group(GroupTreatment.TECHNICAL)
                .price(5)
                .time(10)
                .allFingers(false)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Malowanie - jeden kolor")
                .group(GroupTreatment.PAINTING)
                .price(15)
                .time(10)
                .allFingers(true)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Malowanie kilka kolorów")
                .group(GroupTreatment.PAINTING)
                .price(20)
                .time(10)
                .allFingers(true)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Pyłek")
                .group(GroupTreatment.PAINTING)
                .price(10)
                .time(5)
                .allFingers(true)
                .build());

        treatmentService.save(TreatmentModel.builder()
                .name("Zdobienie")
                .group(GroupTreatment.PAINTING)
                .price(10)
                .time(10)
                .allFingers(true)
                .build());
    }

    private void generateVisit(){
        List<TreatmentEntity> allTreatment = treatmentService.findAll();
        List<UserEntity> allUsers = userService.findAll();

        NewVisit newVisit = NewVisit.builder()
                .startVisitDateTime(LocalDateTime.of(2020,10,4,12,30))
                .user(allUsers.get(1))
                .treatmentList(allTreatment.subList(3,5))
                .comments("Mam nadzieję że będzie ok")
                .build();

        visitService.save(newVisit);
    }


    private void generatePhotoCategories() {
        photoCategoryService.save(PhotoCategoryModel.builder().name("Jeden kolor").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Kilka kolorów").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Pyłek/brokat").build());
        photoCategoryService.save(PhotoCategoryModel.builder().name("Własny rysunek").build());
    }


}
