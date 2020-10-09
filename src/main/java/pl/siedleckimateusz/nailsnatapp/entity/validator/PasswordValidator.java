package pl.siedleckimateusz.nailsnatapp.entity.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

public class PasswordValidator implements ConstraintValidator<Password, char[]> {
    public void initialize(Password constraint) {
    }

    public boolean isValid(char[] password, ConstraintValidatorContext context) {
        boolean digitFlag = false;
        boolean capitalLetterFlag = false;

        if (password!=null){
            for (int i=0; i<password.length;i++){
                if (password[i]>='A' && password[i]<='Z'){
                    capitalLetterFlag = true;
                }
                if (Character.isDigit(password[i])){
                    digitFlag = true;
                }
            }

            return (digitFlag && capitalLetterFlag);
        }
        return false;
    }
}
