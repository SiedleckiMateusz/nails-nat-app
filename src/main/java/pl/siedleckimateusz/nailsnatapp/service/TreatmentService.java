package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.TreatmentMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentModel;
import pl.siedleckimateusz.nailsnatapp.repository.TreatmentRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    private final TreatmentRepo repo;
    private final TreatmentMapper mapper;

    public TreatmentService(TreatmentRepo repo, TreatmentMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public TreatmentEntity save(TreatmentModel treatment) {
        return repo.save(mapper.toSave(treatment));
    }

    public Optional<TreatmentEntity> findById(Long id){
        return repo.findById(id);
    }

    public List<TreatmentEntity> findAll(){
        return repo.findAll();
    }

    public List<TreatmentModel> findAllTreatmentModel(){
        return repo.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
