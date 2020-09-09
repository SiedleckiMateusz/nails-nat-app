package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.GroupTreatment;

@Data
@Builder
public class NewTreatment {

    private GroupTreatment group;

    private String name;

    private Integer time;

    private Integer price;

    private boolean allFingers;

}
