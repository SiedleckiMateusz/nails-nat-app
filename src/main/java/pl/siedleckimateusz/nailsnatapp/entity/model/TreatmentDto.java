package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDto {

    private Long id;

    private String name;

    private Integer time;

    private Integer price;

}
