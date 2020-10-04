package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;

import java.time.LocalDate;

@Data
@Builder
public class UserForm {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String username;

    private String password;

    private String password2;

    private Authority authority;

    private LocalDate birthDate;
}
