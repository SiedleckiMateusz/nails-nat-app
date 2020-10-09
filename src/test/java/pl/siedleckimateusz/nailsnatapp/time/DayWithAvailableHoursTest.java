package pl.siedleckimateusz.nailsnatapp.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DayWithAvailableHoursTest {

    DayWithAvailableHours dayWithAvailableHours;

    @BeforeEach
    void setUp() {
        List<LocalTime> allHours = Arrays.asList(
                LocalTime.of(10,15)
                ,LocalTime.of(10,30)
                ,LocalTime.of(11,15)
                ,LocalTime.of(11,45)
                ,LocalTime.of(11,59)
                ,LocalTime.of(12,15)
                ,LocalTime.of(15,13)
        );

        LocalDate date = LocalDate.of(2020, 5, 12);

        dayWithAvailableHours = new DayWithAvailableHours(date, allHours);
    }

    @Test
    public void should_return_list_with_11_hour() {
//        given
        int hour = 11;
        //when
        Optional<TimesInHour> elevenOptional = dayWithAvailableHours.getAllForHour(hour);

        //then
        System.out.println(elevenOptional.orElse(null));
        assertEquals(3,elevenOptional.get().getTimesList().size());

    }

    @Test
    public void should_get_empty_optional_for_16_hour() {
//        given
        int hour = 16;
        //when
        Optional<TimesInHour> sixteenOpt = dayWithAvailableHours.getAllForHour(hour);


        //then
        assertTrue(sixteenOpt.isEmpty());
    }
}