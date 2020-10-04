package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitToShowForUser;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<VisitEntity,Long> {


    List<VisitEntity> findAllByDateOfVisitBetween(LocalDate start, LocalDate end);

    List<VisitEntity> findAllByUserId(Long id);
}
