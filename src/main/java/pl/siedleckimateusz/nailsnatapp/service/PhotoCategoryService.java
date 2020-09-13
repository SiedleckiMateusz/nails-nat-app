package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoCategoryEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.PhotoCategoryMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoCategoryModel;
import pl.siedleckimateusz.nailsnatapp.repository.PhotoCategoryRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoCategoryService {

    private final PhotoCategoryRepo repo;
    private final PhotoCategoryMapper mapper;

    public PhotoCategoryService(PhotoCategoryRepo repo, PhotoCategoryMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public PhotoCategoryEntity save(PhotoCategoryModel model) {
        return repo.save(mapper.toSave(model));
    }

    public List<PhotoCategoryModel> findAll(){
        return repo.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }
}
