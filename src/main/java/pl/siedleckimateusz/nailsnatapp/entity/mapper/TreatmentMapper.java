package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentDto;

@Component
public class TreatmentMapper implements Mapper<TreatmentDto,TreatmentEntity> {

    @Override
    public TreatmentEntity toSave(TreatmentDto treatment){
        if (treatment==null) return null;

        return TreatmentEntity.builder()
                .name(treatment.getName())
                .price(treatment.getPrice())
                .time(treatment.getTime())
                .build();
    }

    public TreatmentDto toDto(TreatmentEntity t) {
        if (t==null) return null;

        return TreatmentDto.builder()
                .id(t.getId())
                .name(t.getName())
                .price(t.getPrice())
                .time(t.getTime())
                .build();
    }
}
