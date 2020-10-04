package pl.siedleckimateusz.nailsnatapp.time;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TimesInHour {
    private int hour;
    private List<LocalTime> timesList = new ArrayList<>();

    public TimesInHour(int hour, List<LocalTime> timesList) {
        this.hour = hour;
        this.timesList = timesList;
    }

    public TimesInHour(int hour, LocalTime time) {
        this.hour = hour;
        timesList.add(time);
    }

    public void addTime(LocalTime time){
        timesList.add(time);
    }

    @Override
    public String toString() {
        return "h:" + hour +'{'+ timesList + '}';
    }
}
