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

    private LocalDateTime visitDateTime;

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
    public VisitEntity(LocalDateTime visitDateTime, UserEntity user, List<TreatmentEntity> treatmentList, String comments) {
        this.visitDateTime = visitDateTime;
        this.user = user;
        this.treatmentList = treatmentList;
        this.comments = comments;
        this.createdDateTime = LocalDateTime.now();
    }
}
