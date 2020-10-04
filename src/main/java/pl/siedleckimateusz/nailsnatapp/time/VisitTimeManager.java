package pl.siedleckimateusz.nailsnatapp.time;


import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.service.TimeOffService;
import pl.siedleckimateusz.nailsnatapp.service.VisitService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisitTimeManager {

    private final static LocalTime START_WORK = LocalTime.of(8,0);
    private final static LocalTime END_WORK = LocalTime.of(21,0);

    private final TimeOffService timeOffService;
    private final VisitService visitService;

    public VisitTimeManager(TimeOffService timeOffService, VisitService visitService) {
        this.timeOffService = timeOffService;
        this.visitService = visitService;
    }

    public List<LocalDate> getDaysOff(LocalDate start, LocalDate end, int visitLength){

        List<LocalDate> daysOff = new ArrayList<>();
        List<TimeAndDateEvent> allEvents = getAllTimeAndDateEvents(start,end);

        LocalDate actualDate = start;

        while (actualDate.isBefore(end)){
            LocalDate myDate = actualDate;
            List<TimeEvent> timeEventsForDate = allEvents.stream()
                    .filter(t -> t.getDate().equals(myDate))
                    .map(TimeAndDateEvent::getTimeEvent)
                    .collect(Collectors.toList());

            if (!timeEventsForDate.isEmpty()) {
                if (findAllAvailableHours(timeEventsForDate, visitLength).isEmpty()) {
                    daysOff.add(actualDate);
                }
            }

            actualDate = actualDate.plusDays(1);
        }

        return daysOff;
    }

    public boolean isAvailableDateForVisit(LocalDate dateOfVisit, int fullTimeOfVisit) {
        List<LocalDate> daysOff = getDaysOff(dateOfVisit, dateOfVisit, fullTimeOfVisit);

        return daysOff.isEmpty();
    }

    public List<LocalTime> getAvailableHoursForDate(LocalDate chosenDate, long visitTimeInMinutes){
        List<TimeEvent> allEventsOff = findAllEventsWhereDate(chosenDate);

        return findAllAvailableHours(allEventsOff,visitTimeInMinutes);
    }

    public boolean isAvailableTimeForVisit(LocalDate visitDate, LocalTime start, long visitTimeInMinutes){
        List<LocalTime> availableHours = getAvailableHoursForDate(visitDate, visitTimeInMinutes);

        return availableHours.contains(start);
    }



    private List<LocalTime> findAllAvailableHours(List<TimeEvent> allEventsOff, long visitTimeInMinutes){
        List<LocalTime> avaliableHours = new ArrayList<>();

        LocalTime actualTime = START_WORK;

        while (actualTime.isBefore(END_WORK.minusMinutes(visitTimeInMinutes))){
            boolean flag = true;

            for (TimeEvent event:allEventsOff){
                if (!hoursIsAvaliable(event,actualTime,visitTimeInMinutes)){
                    flag = false;
                    break;
                }
            }

            if (flag) avaliableHours.add(actualTime);

            actualTime = actualTime.plusMinutes(15);
        }

        return avaliableHours;
    }

    private List<TimeEvent> findAllEventsWhereDate(LocalDate chosenDate) {
        List<TimeEvent> events =  timeOffService.findAllFromDate(chosenDate);
        events.addAll(visitService.findAllFromDate(chosenDate));

        return events;
    }

    private boolean hoursIsAvaliable(TimeEvent eventOff, LocalTime startTime, long visitLengthInMinutes){
        LocalTime endTime = startTime.plusMinutes(visitLengthInMinutes);

        return (areAfterEnd(eventOff.getEnd(),startTime,endTime) || areBeforeStart(eventOff.getStart(),startTime,endTime));
    }

    private boolean areBeforeStart(LocalTime checkedTime, LocalTime start, LocalTime end){
        return (start.isBefore(checkedTime)&&end.isBefore(checkedTime));
    }

    private boolean areAfterEnd(LocalTime checkedTime, LocalTime start, LocalTime end){
        return (start.isAfter(checkedTime)&&end.isAfter(checkedTime));
    }

    private List<TimeAndDateEvent> getAllTimeAndDateEvents(LocalDate start, LocalDate end){
        List<TimeAndDateEvent> allVisit = visitService.findAllByDateOfVisitBetween(start,end).stream()
                .map(TimeAndDateEvent::new)
                .collect(Collectors.toList());

        allVisit.addAll(timeOffService.findAllBetween(start,end));

        return allVisit.stream().sorted(Comparator.comparing(TimeAndDateEvent::getDate)).collect(Collectors.toList());
    }

}
