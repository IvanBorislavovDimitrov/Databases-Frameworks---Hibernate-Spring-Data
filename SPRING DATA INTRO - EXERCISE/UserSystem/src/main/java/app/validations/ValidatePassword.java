package app.validations;

import app.anotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePassword implements ConstraintValidator<Password, String> {

    private Password constraint;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext constraintValidatorContext) {

        if (pass.length() < this.constraint.minLength() || pass.length() >= this.constraint.maxLength()) {
            return false;
        }
        if (this.constraint.containsLowerCase()) {
            boolean containsLow = false;
            for (int i = 0; i < pass.length(); i++) {
                if (Character.isLowerCase(pass.charAt(i))) {
                    containsLow = true;
                    break;
                }
            }

            if (! containsLow) {
                return false;
            }
        }

        if (this.constraint.containsUpperCase()) {
            boolean containsUp = false;
            for (int i = 0; i < pass.length(); i++) {
                if (Character.isUpperCase(pass.charAt(i))) {
                    containsUp = true;
                    break;

                }
            }

            if (!containsUp) {
                return false;
            }
        }

        if (this.constraint.containsDigit()) {
            boolean containsDigit = false;
            for (int i = 0; i < pass.length(); i++) {
                if (Character.isDigit(pass.charAt(i))) {
                    containsDigit = true;
                    break;

                }
            }

            if (!containsDigit) {
                return false;
            }
        }

        if (this.constraint.containsSpecialSymbol()) {
            boolean specialSymbol = false;
            for (int i = 0; i < pass.length(); i++) {
                if (pass.charAt(i) == '!' || pass.charAt(i) == '@' || pass.charAt(i) == '#' || pass.charAt(i) == '$' ||
                        pass.charAt(i) == '%' || pass.charAt(i) == '^' || pass.charAt(i) == '&' || pass.charAt(i) == '*' ||
                        pass.charAt(i) == '(' || pass.charAt(i) == ')' || pass.charAt(i) == '<' || pass.charAt(i) == '>' || pass.charAt(i) == '?') {
                    specialSymbol = true;
                    break;

                }
            }
            if (!specialSymbol) {
                return false;
            }
        }

        return true;
    }
}
