package pl.siedleckimateusz.nailsnatapp.entity.validator;

import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.repository.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final UserRepo userRepo;

    public UniqueLoginValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        if (login!=null){
            Optional<UserEntity> byUsername = userRepo.findByUsername(login);
            return byUsername.isEmpty();
        }

        return false;
    }
}
