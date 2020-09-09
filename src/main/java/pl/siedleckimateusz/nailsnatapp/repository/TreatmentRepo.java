package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;

@Repository
public interface TreatmentRepo extends JpaRepository<TreatmentEntity,Long> {
}
