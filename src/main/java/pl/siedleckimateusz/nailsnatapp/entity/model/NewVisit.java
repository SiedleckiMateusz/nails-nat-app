package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class NewVisit {

    private LocalDateTime visitDateTime;
    private UserEntity user;
    private List<TreatmentEntity> treatmentList;
    private String comments;
}
