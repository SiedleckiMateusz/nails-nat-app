package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentDto;

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
        TreatmentDto sample_treatment = TreatmentDto.builder()
                .name("Sample Treatment")
                .time(20)
                .price(10)
                .build();

//    when
        TreatmentEntity treatmentEntity = treatmentMapper.toSave(sample_treatment);
//    given
        assertEquals(treatmentEntity.getName(),sample_treatment.getName());

    }

    @Test
    void mapNullValue(){
//        when
        TreatmentEntity treatmentEntity = treatmentMapper.toSave(null);

//        then

        assertNull(treatmentEntity);
    }
}