package pl.siedleckimateusz.nailsnatapp.time;

import lombok.Builder;
import lombok.Getter;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;

import java.time.Duration;
import java.time.LocalTime;

@Getter
public class TimeEvent {

    private LocalTime start;
    private LocalTime end;
    private Long durationInMinutes;


    @Builder
    public TimeEvent(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
        durationInMinutes = Duration.between(start,end).toMinutes();
    }

    @Builder
    public TimeEvent(LocalTime start, Long durationInMinutes) {
        this.start = start;
        this.durationInMinutes = durationInMinutes;
        this.end = start.plusMinutes(durationInMinutes);
    }

    public TimeEvent(TimeOffEntity entity) {
        this.start = entity.getStartTime();
        this.end = entity.getEndTime();
        this.durationInMinutes = Duration.between(start,end).toMinutes();
    }

    public TimeEvent(VisitEntity visitEntity) {
        this.start = visitEntity.getStartTime();
        this.end = visitEntity.getEndTime();
        this.durationInMinutes = Duration.between(start,end).toMinutes();
    }
}
