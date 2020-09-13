package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitForm {

    private Long id;

    private String startDateTime;

    private String comment;

    private UserEntity user;

    private List<TreatmentForm> treatmentFormList = new ArrayList<>();
}
