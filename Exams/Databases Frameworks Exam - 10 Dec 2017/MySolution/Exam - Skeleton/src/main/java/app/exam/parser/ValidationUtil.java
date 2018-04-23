package app.exam.parser;

import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean validate(T obj) {
        return obj != null && validator.validate(obj).size() == 0;
    }
}
