package app.validations;

import app.anotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("((?:^| )[A-Za-z][A-Za-z._\\-0-9]+)@([A-Za-z][A-Za-z-_]+)(\\.[A-Za-z]+)+(?:$|.| |,)");
        Matcher matcher = pattern.matcher(s);

        return matcher.find();
    }
}
