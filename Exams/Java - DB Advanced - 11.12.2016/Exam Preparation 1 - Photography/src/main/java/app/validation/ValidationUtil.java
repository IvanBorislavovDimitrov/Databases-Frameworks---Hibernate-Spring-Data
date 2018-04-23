package app.validation;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> boolean isValid(T obj) {
        int count = validator.validate(obj).size();
        return obj != null && validator.validate(obj).size() == 0;
    }
}
