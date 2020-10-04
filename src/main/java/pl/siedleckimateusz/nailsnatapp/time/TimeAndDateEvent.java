package pl.siedleckimateusz.nailsnatapp.time;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;

import java.time.LocalDate;


@Data
public class TimeAndDateEvent {

    private LocalDate date;
    private TimeEvent timeEvent;

    @Builder
    public TimeAndDateEvent(LocalDate date, TimeEvent timeEvent) {
        this.date = date;
        this.timeEvent = timeEvent;
    }

    public TimeAndDateEvent(LocalDate date, TimeOffEntity timeOffEntity) {
        this.date = date;
        this.timeEvent = TimeEvent.builder()
                        .start(timeOffEntity.getStartTime())
                        .end(timeOffEntity.getEndTime())
                        .build();
    }

    public TimeAndDateEvent(VisitEntity entity) {
        this.date = entity.getDateOfVisit();
        this.timeEvent = TimeEvent.builder()
                .start(entity.getStartTime())
                .end(entity.getEndTime())
                .build();
    }

    @Override
    public String toString() {
        return date + ", " + timeEvent.getStart() + " - " +timeEvent.getEnd();
    }
}
