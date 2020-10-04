package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Scope("session")
public class UserToSession {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String username;

    private Authority authority;

    private int extraTime;

    public String getFirstAndLastName(){
        return firstName+" "+lastName;
    }
}
