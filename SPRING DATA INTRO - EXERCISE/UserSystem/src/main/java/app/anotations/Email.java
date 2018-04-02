package app.anotations;

import app.validations.ValidationEmail;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidationEmail.class)
public @interface Email {
    String message() default "Please enter valid email!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
