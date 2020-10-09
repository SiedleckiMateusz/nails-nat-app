package pl.siedleckimateusz.nailsnatapp.time;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class DayWithAvailableHours {

    private String date;
    private String dayOfWeek;
    private List<TimesInHour> timesInHours;


    public DayWithAvailableHours(LocalDate date, List<LocalTime> allHours) {
        this.date = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dayOfWeek = setDayOfWeek(date.getDayOfWeek().getValue());
        this.timesInHours = sortHours(allHours);
    }

    private List<TimesInHour> sortHours(List<LocalTime> allHours) {
        List<TimesInHour> timesInHourList = new ArrayList<>();

        for (LocalTime time: allHours){
            Optional<TimesInHour> timesInHourOpt = timesInHourList.stream()
                    .filter(t -> t.getHour() == time.getHour())
                    .findFirst();
            if (timesInHourOpt.isEmpty()){
                timesInHourList.add(new TimesInHour(time.getHour(),time));
            }else {
                timesInHourOpt.get().addTime(time);
            }
        }
        return timesInHourList;
    }

    public Optional<TimesInHour> getAllForHour(int hour){
        return timesInHours.stream().filter(t->t.getHour()==hour).findFirst();
    }

    public static String setDayOfWeek(int value) {
        switch (value){
            case 1:
                return "Poniedziałek";
            case 2:
                return "Wtorek";
            case 3:
                return "Środa";
            case 4:
                return "Czwartek";
            case 5:
                return "Piątek";
            case 6:
                return "Sobota";
            case 7:
                return "Niedziela";
        }

        throw new IllegalArgumentException("Nie poprawna wartość. Nie ma takiego dnia tygodnia!");
    }
}
