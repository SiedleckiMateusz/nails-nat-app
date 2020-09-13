package pl.siedleckimateusz.nailsnatapp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String url;

    private PhotoType type;

    @ManyToMany
    @JoinTable(name = "photo_categories"
            ,joinColumns = @JoinColumn(name = "photo_id")
            ,inverseJoinColumns = @JoinColumn(name = "photo_category_id"))
    private List<PhotoCategoryEntity> photoCategories;

    @Builder
    public PhotoEntity(String name, String description, String url, PhotoType type, List<PhotoCategoryEntity> photoCategories) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.type = type;
        this.photoCategories = photoCategories;
    }
}
