package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.TreatmentForm;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitForm;
import pl.siedleckimateusz.nailsnatapp.service.TreatmentService;
import pl.siedleckimateusz.nailsnatapp.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/visit")
public class VisitController {

    private final TreatmentService treatmentService;
    private final UserService userService;

    public VisitController(TreatmentService treatmentService, UserService userService) {
        this.treatmentService = treatmentService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String get(Model model) {
        model.addAttribute("visit",new VisitForm());
        model.addAttribute("allTreatments",treatmentService.findAllTreatmentModel());
        model.addAttribute("treatmentForm",new TreatmentForm());

        return "visit/form";
    }

    @PostMapping
    public String get(@ModelAttribute VisitForm visit) {
        Long userId = ((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        if (userId!=null){
            Optional<UserEntity> userOptional = userService.findById(userId);
            userOptional.ifPresent(visit::setUser);
        }


        System.out.println(visit);

        return "redirect:/visit/new";
    }
}
