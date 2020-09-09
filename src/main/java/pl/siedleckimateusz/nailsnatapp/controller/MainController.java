package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping
    public String get() {
        return "index";
    }

    @PostMapping
    public String getPost() {
        return "index";
    }
}
