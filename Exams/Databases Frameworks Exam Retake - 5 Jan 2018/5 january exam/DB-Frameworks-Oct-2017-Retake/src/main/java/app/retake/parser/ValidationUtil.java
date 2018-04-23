package app.retake.parser;


import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean isValid(T t) {
        return t != null && validator.validate(t).size() == 0;
    }
}
