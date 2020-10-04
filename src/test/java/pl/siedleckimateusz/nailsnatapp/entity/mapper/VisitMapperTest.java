package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapperTest {
    
    VisitMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new VisitMapper();
    }

    void mapNullValue(){
//        when
        VisitEntity visitEntity = mapper.toSave(null);
//        then
        assertNull(visitEntity);
    }
}