package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoCategoryEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.PhotoCategoryModel;

@Component
public class PhotoCategoryMapper implements Mapper<PhotoCategoryModel, PhotoCategoryEntity> {

    @Override
    public PhotoCategoryEntity toSave(PhotoCategoryModel newObj) {
        return newObj == null ? null : PhotoCategoryEntity.builder()
                .name(newObj.getName())
                .build();
    }

    public PhotoCategoryModel toModel(PhotoCategoryEntity entity) {
        return entity == null ? null : PhotoCategoryModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
