package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class EmployeeController {

    @GetMapping
    public String getPanel() {
        return "employee/index";
    }

    @GetMapping("new-visit")
    public String newVisit() {
        return "employee/new-visit";
    }

    @GetMapping("my-calendar")
    public String getCalendar() {
        return "employee/calendar";
    }

    @GetMapping("time-off")
    public String getTimeOff() {
        return "employee/time-off";
    }

    @GetMapping("clients")
    public String getClients() {
        return "employee/clients";
    }

    @GetMapping("dictionary")
    public String getDictionary() {
        return "employee/dictionary";
    }
}
