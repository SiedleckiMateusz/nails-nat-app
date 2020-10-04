package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.TreatmentMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentDto;
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

    public TreatmentEntity save(TreatmentDto treatment) {
        return repo.save(mapper.toSave(treatment));
    }

    public Optional<TreatmentEntity> findById(Long id){
        return repo.findById(id);
    }


    public Optional<TreatmentDto> findByIdDto(Long id){
        Optional<TreatmentEntity> byId = findById(id);

        if (byId.isEmpty()) return Optional.empty();

        return byId.map(mapper::toDto);
    }

    public List<TreatmentEntity> findAll(){
        return repo.findAll();
    }

    public List<TreatmentDto> findAllTreatmentDto(){
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
