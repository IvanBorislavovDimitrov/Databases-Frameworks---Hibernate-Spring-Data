package app.validations;

import app.anotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;
    private boolean containsUpperCase;
    private boolean containsLowerCase;
    private boolean containsDigit;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.containsUpperCase = constraintAnnotation.containsUpperCase();
        this.containsLowerCase = constraintAnnotation.containsLowerCase();
        this.containsDigit = constraintAnnotation.containsDigit();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password.length() < this.minLength || password.length() > this.maxLength) {
            return false;
        }

        return isThereDigit(password) && isThereLowerCase(password) && isThereUpper(password);
    }

    private boolean isThereDigit(String password) {
        if (this.containsDigit) {
            boolean isThereDigit = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    isThereDigit = true;
                    break;
                }
            }
            return isThereDigit;
        }

        return true;
    }

    private boolean isThereLowerCase(String password) {
        if (this.containsLowerCase) {
            boolean isThereLower = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLowerCase(password.charAt(i))) {
                    isThereLower = true;
                    break;
                }
            }
            return isThereLower;
        }

        return true;
    }

    private boolean isThereUpper(String password) {
        if (this.containsUpperCase) {
            boolean isThereUpper = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    isThereUpper = true;
                    break;
                }
            }
            return isThereUpper;
        }

        return true;
    }
}
