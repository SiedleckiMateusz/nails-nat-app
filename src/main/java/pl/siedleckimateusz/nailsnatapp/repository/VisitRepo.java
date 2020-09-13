package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitTime;

import java.time.LocalDateTime;

@Repository
public interface VisitRepo extends JpaRepository<VisitEntity,Long> {

    VisitTime findAllByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
