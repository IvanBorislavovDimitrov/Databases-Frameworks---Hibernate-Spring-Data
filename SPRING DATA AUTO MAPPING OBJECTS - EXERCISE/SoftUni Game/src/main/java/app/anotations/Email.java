package app.anotations;

import app.validations.EmailValidator;
import app.validations.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default
            "Incorrect email!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
