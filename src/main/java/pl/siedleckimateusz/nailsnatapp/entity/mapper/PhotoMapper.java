package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoCategoryEntity;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewPhoto;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoCategoryModel;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoForm;
import pl.siedleckimateusz.nailsnatapp.repository.PhotoCategoryRepo;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoMapper implements Mapper<NewPhoto, PhotoEntity> {

    private final PhotoCategoryRepo photoCategoryRepo;

    public PhotoMapper(PhotoCategoryRepo photoCategoryRepo) {
        this.photoCategoryRepo = photoCategoryRepo;
    }


    @Override
    public PhotoEntity toSave(NewPhoto newObj) {
        if (newObj==null){
            return null;
        }

        List<Long> photoCategoryIdList = newObj.getPhotoCategories().stream().map(PhotoCategoryModel::getId).collect(Collectors.toList());
        List<PhotoCategoryEntity> photoCategoryEntities = photoCategoryRepo.findAllById(photoCategoryIdList);

        return PhotoEntity.builder()
                .description(newObj.getDescription())
                .name(newObj.getName())
                .url(newObj.getUrl())
                .photoCategories(photoCategoryEntities)
                .type(newObj.getType())
                .build();
    }

    public PhotoEntity toSave(PhotoForm newObj) {
        if (newObj==null){
            return null;
        }
        List<PhotoCategoryEntity> photoCategoryEntities = photoCategoryRepo.findAllById(newObj.getPhotoCategoryIds());

        return PhotoEntity.builder()
                .description(newObj.getDescription())
                .name(newObj.getName())
                .url(newObj.getUrl())
                .photoCategories(photoCategoryEntities)
                .type(newObj.getType())
                .build();
    }


}
