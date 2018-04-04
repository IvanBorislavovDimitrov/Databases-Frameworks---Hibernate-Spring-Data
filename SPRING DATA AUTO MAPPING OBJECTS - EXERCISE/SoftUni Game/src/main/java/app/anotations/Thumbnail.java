package app.anotations;

import app.validations.ThumbnailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@Constraint(validatedBy = ThumbnailValidator.class)
public @interface Thumbnail {
    String message() default
            "Incorrect thumbnail!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
