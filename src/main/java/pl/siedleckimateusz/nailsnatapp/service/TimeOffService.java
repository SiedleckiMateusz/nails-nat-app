package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.repository.TimeOffRepo;
import pl.siedleckimateusz.nailsnatapp.time.TimeAndDateEvent;
import pl.siedleckimateusz.nailsnatapp.time.TimeEvent;

import java.time.LocalDate;
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

    public TimeOffEntity save(TimeOffEntity entity) {
        return timeOffRepo.save(entity);
    }

    public List<TimeOffEntity> saveAll(List<TimeOffEntity> entities) {
        return timeOffRepo.saveAll(entities);
    }

    public List<TimeOffEntity> findAll() {
        return timeOffRepo.findAll();
    }

    public List<TimeEvent> findAllFromDate(LocalDate date) {
        return findAll().stream()
                .sorted(Comparator.comparing(TimeOffEntity::getDayOfEvent))
                .filter(t -> t.concernsOnDate(date))
                .map(TimeEvent::new)
                .collect(Collectors.toList());
    }

    public List<TimeAndDateEvent> findAllBetween(LocalDate start, LocalDate end) {
        List<TimeOffEntity> all = findAll();
        List<TimeAndDateEvent> myEvents = new ArrayList<>();

        LocalDate actual = start;

        while (actual.isBefore(end)) {

            for (TimeOffEntity timeOff : all) {
                if (timeOff.concernsOnDate(actual)) {
                    myEvents.add(new TimeAndDateEvent(actual, timeOff));
                }
            }

            actual = actual.plusDays(1);
        }

        return myEvents;

    }
}
