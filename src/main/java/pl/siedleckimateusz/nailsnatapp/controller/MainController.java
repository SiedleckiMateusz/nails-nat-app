package pl.siedleckimateusz.nailsnatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String get() {
        return "index";
    }

    @PostMapping
    public String getPost() {
        return "redirect:/";
    }
}
