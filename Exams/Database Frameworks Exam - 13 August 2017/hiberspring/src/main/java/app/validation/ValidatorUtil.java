package app.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Set<ConstraintViolation<T>> getExceptions(T obj) {
        Set<ConstraintViolation<T>> validate = validator.validate(obj);
        return validate;
    }

    public static <T> boolean isValid(T obj) {
        return obj != null && validator.validate(obj).size() == 0;
    }
}
