package pl.siedleckimateusz.nailsnatapp.entity.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.time.DayWithAvailableHours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("session")
public class VisitForm {

    private LocalDate dateOfVisit;

    private LocalTime startTime;

    private String moreInfo;

    private UserEntity user;

    private TreatmentEntity treatment;

    public String getDayOfWeek(){
        return DayWithAvailableHours.setDayOfWeek(dateOfVisit.getDayOfWeek().getValue());
    }

    public void clearVisit() {
        this.dateOfVisit = null;
        this.startTime = null;
        this.moreInfo = null;
        this.treatment = null;
    }

    public int getFullTimeOfVisit(){
        return treatment.getTime()+user.getExtraTime();
    }

    public String getStringDate(){
        return dateOfVisit.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setTreatment(TreatmentEntity treatment) {
        this.treatment = treatment;
        this.dateOfVisit = null;
        this.startTime = null;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
        this.startTime = null;
    }
}
