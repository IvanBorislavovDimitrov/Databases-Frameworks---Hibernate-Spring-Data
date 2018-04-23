package app.exam.parser;

import javax.validation.Validation;
import javax.validation.Validator;

public final class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ValidationUtil() {
    }

    public static <T> boolean isValid(T t) {
        int count = validator.validate(t).size();
        return t != null && validator.validate(t).size() == 0;
    }
}
