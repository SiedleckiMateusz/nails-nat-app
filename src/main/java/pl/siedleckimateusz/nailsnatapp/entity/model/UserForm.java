package pl.siedleckimateusz.nailsnatapp.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siedleckimateusz.nailsnatapp.entity.Authority;
import pl.siedleckimateusz.nailsnatapp.entity.validator.Password;
import pl.siedleckimateusz.nailsnatapp.entity.validator.UniqueLogin;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    @NotEmpty(message = "Imię jest wymagane bo nie wiemy jak się do Ciebie zwracać")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "E-mail jest wymagany")
    @Email(message = "Poprawny format: mail@example.com")
    private String email;

    @NotEmpty(message = "Numer telefonu jest potrzebny do szybkiego kontaktu z klientem")
    @Size(min = 9,max = 12,message = "Poprawny format: 123456789 lub +48123456789")
    private String phoneNumber;

    @NotEmpty(message = "Login jest wymagana ponieważ służy do logowania. Musi być unikatowy")
    @UniqueLogin(message = "Wybrany login jest już zajęty. Wybierz inny")
    private String username;

    @NotEmpty(message = "Hasło jest wymagane")
    @Size(min = 6,message = "Hasło musi zawierać min 6 znaków")
    @Password(message = "Hasło musi zawierać przynajmniej jedną dużą literę i cyfrę")
    private char[] password = new char[]{};

    @NotEmpty(message = "Hasło jest wymagane")
    private char[] password2;

    private Authority authority;

    @NotNull(message = "Data urodzenia jest wymagana do sprawdzenia pełnoletności")
    private LocalDate birthDate;

    public void setPassword(String password) {
        this.password = password.toCharArray();
    }

    public void setPassword2(String password2) {
        this.password2 = password2.toCharArray();
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setPassword2(char[] password2) {
        this.password2 = password2;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate!=null && !birthDate.isEmpty()){
            String[] split = birthDate.split("-");
            this.birthDate = LocalDate.of(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2]));
        }else {
            this.birthDate = null;
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        if (birthDate==null) return "";
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getBirthDateLocalDate(){
        return this.birthDate;
    }


    public String getStringPassword() {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c:password){
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
