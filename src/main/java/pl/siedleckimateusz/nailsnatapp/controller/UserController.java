package pl.siedleckimateusz.nailsnatapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.entity.model.UserForm;
import pl.siedleckimateusz.nailsnatapp.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/new")
    public String newUserForm(UserForm userForm) {

        return "registration";
    }

    @PostMapping
    public String saveNew(@Valid UserForm userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            return "registration";
        }

        if (!Arrays.equals(userForm.getPassword(),userForm.getPassword2())){
            model.addAttribute("passwordMatch","Hasła nie są takie same. Spróbuj ponownie");
            return "registration";
        }

        userForm.setAuthority(Authority.CLIENT);
        UserEntity save = userService.save(userForm);

        if (save==null){
            return "redirect:/error";
        }

        return "redirect:/login?createdAccount";
    }


}
