package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.Builder;
import lombok.Data;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;

@Data
@Builder
public class NewUser {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String username;

    private String password;

    private Authority authority;

    private int extraTime;

}
