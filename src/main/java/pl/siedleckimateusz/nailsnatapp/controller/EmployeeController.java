package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitForm;
import pl.siedleckimateusz.nailsnatapp.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Controller
@RequestMapping("/panel")
@SessionScope
public class EmployeeController {

    private final VisitForm visitForm;
    private final UserService userService;

    public EmployeeController(VisitForm visitForm, UserService userService) {
        this.visitForm = visitForm;
        this.userService = userService;
    }

    @GetMapping
    public String getPanel() {
        return "employee/index";
    }

    @GetMapping("/new-visit")
    public String newVisit() {
        return "employee/new-visit";
    }

    @PostMapping("/new-visit")
    public String setClient(String firstName, String lastName, String phoneNumber) {
        if (firstName==null || lastName==null || phoneNumber==null){
            return "redirect:/panel/new-visit";
        }

        UserEntity userEntity = userService.saveOrGetExist(firstName, lastName, phoneNumber);

        visitForm.setUser(userEntity);


        return "redirect:/visit/new";
    }

    @GetMapping("/my-calendar")
    public String getCalendar() {
        return "employee/calendar";
    }

    @GetMapping("/time-off")
    public String getTimeOff() {
        return "employee/time-off";
    }

    @GetMapping("/clients")
    public String getClients() {
        return "employee/clients";
    }

    @GetMapping("/dictionary")
    public String getDictionary() {
        return "employee/dictionary";
    }
}
