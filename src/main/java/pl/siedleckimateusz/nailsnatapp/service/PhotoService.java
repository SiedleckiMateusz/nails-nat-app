package pl.siedleckimateusz.nailsnatapp.service;

import org.springframework.stereotype.Service;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoEntity;
import pl.siedleckimateusz.nailsnatapp.entity.mapper.PhotoMapper;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewPhoto;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoForm;
import pl.siedleckimateusz.nailsnatapp.repository.PhotoRepo;

@Service
public class PhotoService {
    private final PhotoRepo repo;
    private final PhotoMapper mapper;

    public PhotoService(PhotoRepo repo, PhotoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public PhotoEntity save(NewPhoto photo){

        PhotoEntity save = repo.save(mapper.toSave(photo));

        save.getPhotoCategories().forEach(cat->cat.getPhotos().add(save));

        return save;
    }

    public PhotoEntity save(PhotoForm photo){
        PhotoEntity save = repo.save(mapper.toSave(photo));

        save.getPhotoCategories().forEach(cat->cat.getPhotos().add(save));

        return save;
    }
}
