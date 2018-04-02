package app.validations;

import app.anotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationEmail implements ConstraintValidator<Email, String> {

    private static final String EMAIL_REGEX = "^((?:^| )[A-Za-z][A-Za-z._-]+)@([A-Za-z][A-Za-z-_]+)(\\.[A-Za-z]+)+(?:$|.| |,)$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(s);

        return matcher.find();

    }
}
