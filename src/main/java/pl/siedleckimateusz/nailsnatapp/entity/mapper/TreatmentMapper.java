package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentModel;

@Component
public class TreatmentMapper implements Mapper<TreatmentModel,TreatmentEntity> {

    @Override
    public TreatmentEntity toSave(TreatmentModel treatment){
        return treatment == null ? null : TreatmentEntity.builder()
                .name(treatment.getName())
                .groupTreatment(treatment.getGroup())
                .price(treatment.getPrice())
                .time(treatment.getTime())
                .allFinger(treatment.isAllFingers())
                .build();
    }

    public TreatmentModel toModel(TreatmentEntity t) {
        if (t==null) return null;

        return TreatmentModel.builder()
                .allFingers(t.isAllFinger())
                .group(t.getGroupTreatment())
                .id(t.getId())
                .name(t.getName())
                .price(t.getPrice())
                .time(t.getTime())
                .build();
    }
}
