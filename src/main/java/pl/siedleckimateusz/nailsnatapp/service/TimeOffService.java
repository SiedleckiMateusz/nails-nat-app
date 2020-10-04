package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.repository.TimeOffRepo;
import pl.siedleckimateusz.nailsnatapp.time.TimeAndDateEvent;
import pl.siedleckimateusz.nailsnatapp.time.TimeEvent;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeOffService {

    private final TimeOffRepo timeOffRepo;

    public TimeOffService(TimeOffRepo timeOffRepo) {
        this.timeOffRepo = timeOffRepo;
    }

    public TimeOffEntity save(TimeOffEntity entity){
        return timeOffRepo.save(entity);
    }

    public List<TimeOffEntity> saveAll(List<TimeOffEntity> entities){
        return timeOffRepo.saveAll(entities);
    }

    public List<TimeOffEntity> findAll(){
        return timeOffRepo.findAll();
    }

    public List<TimeEvent> findAllFromDate(LocalDate date) {
        return findAll().stream()
                .sorted(Comparator.comparing(TimeOffEntity::getDayOfEvent))
                .filter(t->t.concernsOnDate(date))
                .map(TimeEvent::new)
                .collect(Collectors.toList());

    }

    public List<TimeAndDateEvent> findAllBetween(LocalDate start, LocalDate end){
        List<TimeOffEntity> all = findAll();
        List<TimeAndDateEvent> myEvents = new ArrayList<>();

        LocalDate actual = start;

        while (actual.isBefore(end)){

            for (TimeOffEntity timeOff:all){
                if (timeOff.concernsOnDate(actual)){
                    myEvents.add(new TimeAndDateEvent(actual,timeOff));
                }
            }

            actual = actual.plusDays(1);
        }

        return myEvents;

    }

//    public List<TimeOffEntity> saveAllForDays(LocalDateTime start,LocalDateTime end, int days){
//        LocalDateTime presentStart = start;
//        LocalDateTime presentEnd = end;
//        int counter = 0;
//
//        List<TimeOffEntity> toSave = new ArrayList<>();
//        toSave.add(new TimeOffEntity(presentStart,presentEnd));
//
//        while (counter<days){
//            toSave.add(new TimeOffEntity(
//                    presentStart.plusDays(1)
//                    ,presentEnd.plusDays(1)
//            ));
//
//            counter++;
//        }
//
//        return timeOffRepo.saveAll(toSave);
//    }
//
//    public List<TimeOffEntity> saveAllForDayOfWeek(LocalDateTime start, LocalDateTime end, DayOfWeek dayOfWeek, int days){
//        LocalDateTime presentStart = start;
//        LocalDateTime presentEnd = end;
//        int counter = 0;
//
//        List<TimeOffEntity> toSave = new ArrayList<>();
//
//        while (counter<days){
//            if (presentStart.getDayOfWeek().equals(dayOfWeek)){
//                toSave.add(new TimeOffEntity(presentStart,presentEnd));
//
//                counter++;
//            }
//
//            presentStart = presentStart.plusDays(1);
//            presentEnd = presentEnd.plusDays(1);
//        }
//
//        return timeOffRepo.saveAll(toSave);
//    }
}
