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

    private String name;

    private Integer time;

    private Integer price;

    @OneToMany(mappedBy = "treatment")
    private List<VisitEntity> visitsList;

    @Builder
    public TreatmentEntity( String name, Integer time, Integer price) {
        this.name = name;
        this.time = time;
        this.price = price;
    }
}

