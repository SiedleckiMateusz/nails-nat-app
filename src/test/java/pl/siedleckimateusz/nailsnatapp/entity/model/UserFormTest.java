package pl.siedleckimateusz.nailsnatapp.entity.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserFormTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void should_get_any_validation_error() {
    //    given
        UserForm user = new UserForm();
        //    when
        Set<ConstraintViolation<UserForm>> validate = validator.validate(user);
        //    then
//        System.out.println(validate.stream().filter(u -> u.getPropertyPath().toString().contains("password")).findFirst().orElse(null));
        assertFalse(validate.isEmpty());
    }

    @Test
    void should_get_string_password() {
//        given
        UserForm user = UserForm.builder().password(new char[]{'m', 'a', 't'}).build();

//        then
        assertEquals("mat",user.getStringPassword());
    }
}