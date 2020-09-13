package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoEntity;

@Repository
public interface PhotoRepo extends JpaRepository<PhotoEntity, Long> {
}
