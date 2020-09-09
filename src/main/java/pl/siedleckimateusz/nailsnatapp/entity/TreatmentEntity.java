package pl.siedleckimateusz.nailsnatapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "treatment")
public class TreatmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GroupTreatment groupTreatment;

    private String name;

    private Integer time;

    private Integer price;

    private boolean allFinger;

    @ManyToMany(mappedBy = "treatmentList")
    private List<VisitEntity> visitsList;

    @Builder
    public TreatmentEntity(GroupTreatment groupTreatment, String name, Integer time, Integer price, boolean allFinger) {
        this.groupTreatment = groupTreatment;
        this.name = name;
        this.time = time;
        this.price = price;
        this.allFinger = allFinger;
    }
}

