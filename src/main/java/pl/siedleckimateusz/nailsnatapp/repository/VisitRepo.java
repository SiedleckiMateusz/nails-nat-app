package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;

@Repository
public interface VisitRepo extends JpaRepository<VisitEntity,Long> {
}
