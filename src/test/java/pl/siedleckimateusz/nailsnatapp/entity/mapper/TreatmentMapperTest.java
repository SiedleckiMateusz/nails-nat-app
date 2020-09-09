package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.GroupTreatment;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewTreatment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TreatmentMapperTest {

    private TreatmentMapper treatmentMapper;

    @BeforeEach
    void setUp() {
        treatmentMapper = new TreatmentMapper();
    }

    @Test
    void mapToEntity() {
//    given
        NewTreatment sample_treatment = NewTreatment.builder()
                .name("Sample Treatment")
                .allFingers(true)
                .time(20)
                .group(GroupTreatment.TECHNICAL)
                .price(10)
                .build();

//    when
        TreatmentEntity treatmentEntity = treatmentMapper.toEntity(sample_treatment);
//    given
        assertEquals(treatmentEntity.getName(),sample_treatment.getName());

    }

    @Test
    void mapNullValue(){
//        when
        TreatmentEntity treatmentEntity = treatmentMapper.toEntity(null);

//        then

        assertNull(treatmentEntity);
    }
}