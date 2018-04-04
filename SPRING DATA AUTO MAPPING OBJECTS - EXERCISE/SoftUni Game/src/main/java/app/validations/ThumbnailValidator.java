package app.validations;

import app.anotations.Thumbnail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ThumbnailValidator implements ConstraintValidator<Thumbnail, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && (s.startsWith("http://") || s.startsWith("https://"));
    }
}
