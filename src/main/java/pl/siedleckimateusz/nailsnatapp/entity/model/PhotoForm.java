package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pl.siedleckimateusz.nailsnatapp.entity.PhotoType;

import java.util.List;

@Data
@NoArgsConstructor
public class PhotoForm {
    private MultipartFile file;
    private String name;
    private String description;
    private PhotoType type;
    private List<Long> photoCategoryIds;
    private String url;
}
