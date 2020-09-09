package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewTreatment;

@Component
public class TreatmentMapper implements Mapper<NewTreatment,TreatmentEntity> {

    @Override
    public TreatmentEntity toEntity(NewTreatment treatment){
        return treatment == null ? null : TreatmentEntity.builder()
                .name(treatment.getName())
                .groupTreatment(treatment.getGroup())
                .price(treatment.getPrice())
                .time(treatment.getTime())
                .allFinger(treatment.isAllFingers())
                .build();
    }

}
