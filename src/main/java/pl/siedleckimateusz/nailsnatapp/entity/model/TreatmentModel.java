package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siedleckimateusz.nailsnatapp.entity.GroupTreatment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentModel {

    private Long id;

    private GroupTreatment group;

    private String name;

    private Integer time;

    private Integer price;

    private boolean allFingers;

}
