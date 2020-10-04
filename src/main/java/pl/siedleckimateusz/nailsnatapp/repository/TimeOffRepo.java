package pl.siedleckimateusz.nailsnatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;

@Repository
public interface TimeOffRepo extends JpaRepository<TimeOffEntity,Long> {


}
