package pl.siedleckimateusz.nailsnatapp.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.Repeat;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.VisitMapper;
import pl.siedleckimateusz.nailsnatapp.repository.TimeOffRepo;
import pl.siedleckimateusz.nailsnatapp.repository.VisitRepo;
import pl.siedleckimateusz.nailsnatapp.service.TimeOffService;
import pl.siedleckimateusz.nailsnatapp.service.VisitService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VisitTimeManagerTest {

    VisitTimeManager timeManager;
    TimeOffRepo timeOffRepo;
    VisitRepo visitRepo;

    @BeforeEach
    void setUp() {
        timeOffRepo = mock(TimeOffRepo.class);
        visitRepo = mock(VisitRepo.class);
        timeManager = new VisitTimeManager(new TimeOffService(timeOffRepo),new VisitService(visitRepo,new VisitMapper()));

    }

    /*
    Week 7-13.09.2020

    TimesOff:
     # 31.08 every Monday 8-16
     # 10.08 every Tuesday 8-16 to 02.09
     # 11.09 Friday allDay

     Visits:
     # 7.09 Monday 17:15 - 18:00
     # 10.09 Thursday 18:45 - 19:20


    ###################
    07.09 -> 8:00 - off - 16:00 - FREE - 17:15 - visit - 18:00 - FREE - 21:00
    08.08 -> 8:00 - off - 16:00 - FREE - 21:00
    09.09 -> 8:00 - FREE - 21:00
    10.09 -> 8:00 - FREE - 18:45 - visit - 19:20 - FREE - 21:00
    11.09 -> 8:00 - off - 21:00
    12.09 -> 8:00 - FREE - 21:00
    13.09 -> 8:00 - FREE - 21:00

     */

    List<VisitEntity> visitList(){
        List<VisitEntity> myList = new ArrayList<>();

        myList.add(VisitEntity.builder()
                .treatment(new TreatmentEntity("zabieg",45,20))
                .dateOfVisit(LocalDate.of(2020,9,7))
                .startTime(LocalTime.of(17,15))
                .build());

        myList.add(VisitEntity.builder()
                .treatment(new TreatmentEntity("zabieg",35,20))
                .dateOfVisit(LocalDate.of(2020,9,10))
                .startTime(LocalTime.of(18,45))
                .build());

        return myList;
    }

    List<TimeOffEntity> timeOffList(){
       List<TimeOffEntity> myList = new ArrayList<>();

       myList.add(TimeOffEntity.builder()
               .dayOfEvent(LocalDate.of(2020,8,31))
               .startTime(LocalTime.of(8,0))
               .endTime(LocalTime.of(16,0))
               .repeat(Repeat.WEEKLY)
               .build());

        myList.add(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020,8,10))
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(16,0))
                .repeat(Repeat.WEEKLY)
                .expirationDate(LocalDate.of(2020,9,2))
                .build());



        myList.add(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020,9,11))
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(21,0))
                .repeat(Repeat.NEVER)
                .build());

        return myList;
    }


    //Behavior drivment development
    @Test
    void should_get_available_hours() {
        //given

        LocalDate date = LocalDate.of(2020, 9, 7);

        int visitTime = 40;

        //when
        when(timeOffRepo.findAll()).thenReturn(timeOffList());
        when(visitRepo.findAll()).thenReturn(visitList());
        List<LocalTime> availableHours = timeManager.getAvailableHoursForDate(date, visitTime);

        //then
        showListHours(date,visitTime,availableHours);

        assertFalse(availableHours.isEmpty());
        assertFalse(availableHours.contains(LocalTime.of(17,30)));
    }

    @Test
    void should_get_empty_list_because_day_is_off(){
        //given
        LocalDate date = LocalDate.of(2020,9,11);

        int treatmentTime = 20;
        //when
        when(timeOffRepo.findAll()).thenReturn(timeOffList());
        when(visitRepo.findAll()).thenReturn(visitList());
        List<LocalTime> availableHours = timeManager.getAvailableHoursForDate(date, treatmentTime);

        //then
        showListHours(date,treatmentTime,availableHours);
        assertTrue(availableHours.isEmpty());
    }


    @Test
    void should_get_one_day_off(){
        //given
        LocalDate start = LocalDate.of(2020,9,1);
        LocalDate end = start.plusMonths(1).minusDays(1);

        int visitTime = 90;

        //when
        when(visitRepo.findAllByDateOfVisitBetween(any(),any())).thenReturn(visitList());
        when(timeOffRepo.findAll()).thenReturn(timeOffList());

        List<LocalDate> daysOff = timeManager.getDaysOff(start,end, visitTime);

        //then
        daysOff.forEach(System.out::println);
        assertFalse(daysOff.isEmpty());
        assertEquals(1,daysOff.size());

    }


    void showListHours(LocalDate date, int treatmentTime, List<LocalTime> availableHours){
        System.out.println("avaliable hours for "+date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+" and visit length "+treatmentTime);
        System.out.println("Hours: ");
        if (availableHours.isEmpty()) {
            System.out.println("No hours available");
        }else {
            availableHours.forEach(System.out::println);
        }
    }
}