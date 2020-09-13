package pl.siedleckimateusz.nailsnatapp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private LocalDateTime createdDateTime;

    private Boolean confirmed = false;

    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "treatment_visit"
            , joinColumns = @JoinColumn(name = "visit_id")
            , inverseJoinColumns = @JoinColumn(name = "treatment_id"))
    private List<TreatmentEntity> treatmentList;

    @Builder
    public VisitEntity(LocalDateTime startDateTime, UserEntity user, List<TreatmentEntity> treatmentList, String comments) {
        this.startDateTime = startDateTime;
        this.treatmentList = treatmentList;
        this.user = user;
        this.comments = comments;
        this.createdDateTime = LocalDateTime.now();
        this.endDateTime = startDateTime.plusMinutes(countVisitTime());
    }

    private long countVisitTime() {
        long sum=0L;

        for (TreatmentEntity t:treatmentList){
            sum+=t.getTime();
        }

        return sum;
    }


    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime.plusMinutes(countVisitTime());
    }

    public void setTreatmentList(List<TreatmentEntity> treatmentList) {
        this.treatmentList = treatmentList;
        this.endDateTime = startDateTime.plusMinutes(countVisitTime());
    }
}
