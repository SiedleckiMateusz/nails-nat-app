package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.VisitMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;
import pl.siedleckimateusz.nailsnatapp.repository.VisitRepo;

@Service
public class VisitService {

    private final VisitRepo repo;
    private final VisitMapper mapper;

    public VisitService(VisitRepo repo, VisitMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public VisitEntity save(NewVisit newVisit){
        return repo.save(mapper.toEntity(newVisit));
    }
}
