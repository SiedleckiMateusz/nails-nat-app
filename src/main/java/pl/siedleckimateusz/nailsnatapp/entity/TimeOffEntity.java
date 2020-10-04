package pl.siedleckimateusz.nailsnatapp.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Entity
@Data
@Table(name = "time_off")
public class TimeOffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    @NotNull
    private LocalDate dayOfEvent;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private Repeat repeat;
    // if duration is null, that mean is infinity
    private LocalDate expirationDate;

    @Builder
    public TimeOffEntity(LocalDate dayOfEvent, LocalTime startTime, LocalTime endTime, Repeat repeat
            , LocalDate expirationDate) {
        this.dayOfEvent = dayOfEvent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeat = repeat;
        this.expirationDate = expirationDate;
    }


    @Override
    public String toString() {
        return dayOfEvent.format(myPatternForDate())+
                ", " + startTime.format(myPatternForTime()) +
                " - " + endTime.format(myPatternForTime());
    }

    public static DateTimeFormatter myPatternForDate(){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    public static DateTimeFormatter myPatternForTime(){
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    public  boolean concernsOnDate(LocalDate date){
        switch (repeat){
            case DAILY:
                if (expirationDate==null){
                    return true;
                }
                return date.isBefore(this.expirationDate);
            case NEVER:
                return date.equals(this.dayOfEvent);
            case WEEKLY:
                return date.getDayOfWeek().equals(this.dayOfEvent.getDayOfWeek());
            case YEARLY:
                return date.getYear()==this.dayOfEvent.getYear();
            case MONTHLY:
                return date.getMonthValue()==this.dayOfEvent.getMonthValue();
            default:
                break;
        }

        throw new UnsupportedOperationException("Missing 'repeat' value");
    }

}
