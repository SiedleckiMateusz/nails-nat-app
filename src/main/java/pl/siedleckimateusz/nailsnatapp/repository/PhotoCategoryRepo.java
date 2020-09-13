package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoCategoryEntity;

@Repository
public interface PhotoCategoryRepo extends JpaRepository<PhotoCategoryEntity,Long> {
}
