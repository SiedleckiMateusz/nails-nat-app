package pl.siedleckimateusz.nailsnatapp.entity.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoCategoryEntity;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoType;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class NewPhoto {

    private String name;
    private String description;
    @NotNull
    private String url;
    private List<PhotoCategoryModel> photoCategories;
    private PhotoType type;
}
