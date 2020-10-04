package pl.siedleckimateusz.nailsnatapp.entity.mapper;

import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.NewVisit;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitForm;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitToShowForUser;

@Component
public class VisitMapper{

    public VisitEntity toSave(VisitForm visitForm) {

        if (visitForm == null) return  null;

        return VisitEntity.builder()
                .user(visitForm.getUser())
                .dateOfVisit(visitForm.getDateOfVisit())
                .startTime(visitForm.getStartTime())
                .treatment(visitForm.getTreatment())
                .moreInfo(visitForm.getMoreInfo())
                .build();
    }

    public VisitToShowForUser toShowForUser(VisitEntity o){
        if (o==null) return null;

        return VisitToShowForUser.builder()
                .id(o.getId())
                .dateOfVisit(o.getDateOfVisit())
                .moreInfo(o.getMoreInfo())
                .startTime(o.getStartTime())
                .treatmentName(o.getTreatment().getName())
                .status(o.getStatus())
                .build();
    }
}
