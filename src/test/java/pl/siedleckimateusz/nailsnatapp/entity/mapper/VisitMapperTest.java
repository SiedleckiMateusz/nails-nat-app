package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapperTest {
    
    VisitMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new VisitMapper();
    }

    @Test
    void mapToEntity() {
//        given
        NewVisit visit = NewVisit.builder()
                .visitDateTime(LocalDateTime.now())
                .treatmentList(Collections.singletonList(new TreatmentEntity()))
                .user(new UserEntity())
                .comments("My comment")
                .build();
//        when
        VisitEntity visitEntity = mapper.toEntity(visit);
//        then

        assertNotNull(visitEntity);
        assertEquals(visit.getComments(),visitEntity.getComments());

    }

    void mapNullValue(){
//        when
        VisitEntity visitEntity = mapper.toEntity(null);
//        then
        assertNull(visitEntity);
    }
}