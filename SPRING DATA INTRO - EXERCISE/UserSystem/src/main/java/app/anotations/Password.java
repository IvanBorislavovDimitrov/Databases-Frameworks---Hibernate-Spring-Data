package app.anotations;

import app.validations.ValidatePassword;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatePassword.class)
public @interface Password {

    int minLength();
    int maxLength();
    boolean containsDigit();
    boolean containsLowerCase();
    boolean containsUpperCase() default false;
    boolean containsSpecialSymbol() default false;

    String message() default "Invalid Password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
