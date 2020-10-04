package pl.siedleckimateusz.nailsnatapp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfVisit;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime createdDateTime;

    private VisitStatus status = VisitStatus.TO_CONFIRM_BY_EMPLOYEE;

    private String moreInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "treatment_id",nullable = false)
    private TreatmentEntity treatment;

    @Builder
    public VisitEntity(LocalDate dateOfVisit, LocalTime startTime
            , String moreInfo, UserEntity user, TreatmentEntity treatment) {
        this.dateOfVisit = dateOfVisit;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(treatment.getTime());
        this.createdDateTime = LocalDateTime.now();
        this.moreInfo = moreInfo;
        this.user = user;
        this.treatment = treatment;
    }
}
