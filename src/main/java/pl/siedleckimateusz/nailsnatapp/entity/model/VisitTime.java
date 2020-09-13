package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitTime {
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


}
