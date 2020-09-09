package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;

@Component
public class VisitMapper implements Mapper<NewVisit, VisitEntity> {


    @Override
    public VisitEntity toEntity(NewVisit newObj) {
        return newObj == null ? null : VisitEntity.builder()
                .visitDateTime(newObj.getVisitDateTime())
                .comments(newObj.getComments())
                .treatmentList(newObj.getTreatmentList())
                .user(newObj.getUser())
                .build();
    }


}
