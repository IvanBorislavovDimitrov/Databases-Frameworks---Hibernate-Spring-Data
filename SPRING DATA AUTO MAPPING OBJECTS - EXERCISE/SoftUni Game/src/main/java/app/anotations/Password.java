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
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    int minLength() default 6;
    int maxLength() default 50;
    boolean containsUpperCase() default false;
    boolean containsLowerCase() default false;
    boolean containsDigit() default false;

    String message() default
            "Incorrect password!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
