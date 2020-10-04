package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.VisitMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitForm;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitToShowForUser;
import pl.siedleckimateusz.nailsnatapp.repository.VisitRepo;
import pl.siedleckimateusz.nailsnatapp.time.TimeEvent;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final VisitRepo repo;
    private final VisitMapper mapper;

    public VisitService(VisitRepo repo, VisitMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public VisitEntity save(VisitEntity visitEntity){
        return repo.save(visitEntity);
    }

    public List<VisitEntity> findAllByDateOfVisitBetween(LocalDate start, LocalDate end){
        return repo.findAllByDateOfVisitBetween(start,end);
    }


    public List<TimeEvent> findAllFromDate(LocalDate date){
        return repo.findAll().stream()
                .filter(v->v.getDateOfVisit().isEqual(date))
                .map(TimeEvent::new)
                .collect(Collectors.toList());
    }

    public VisitEntity save(VisitForm visitForm) {
        return repo.save(mapper.toSave(visitForm));
    }

    public List<VisitToShowForUser> getActualVisitWhereUserId(Long id) {
        LocalDate date = LocalDate.now();

        return repo.findAllByUserId(id).stream()
                .filter(v -> v.getDateOfVisit().isAfter(date))
                .sorted(Comparator.comparing(VisitEntity::getDateOfVisit))
                .map(mapper::toShowForUser)
                .collect(Collectors.toList());
    }

    public List<VisitToShowForUser> getHistoryVisitWhereUserId(Long id) {
        LocalDate date = LocalDate.now();

        return repo.findAllByUserId(id).stream()
                .filter(v->v.getDateOfVisit().isBefore(date))
                .sorted(Comparator.comparing(VisitEntity::getDateOfVisit))
                .map(mapper::toShowForUser)
                .collect(Collectors.toList());
    }
}
