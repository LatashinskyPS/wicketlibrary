package com.intexsoft.library.wicket.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.AbstractValidator;

import java.util.regex.Pattern;

public class CustomNumberValidator extends AbstractValidator<String> {
    private static final String PATTERN_NUMBER = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

    @Override
    protected void onValidate(IValidatable<String> validatable) {
        String numberValue = validatable.getValue();
        ValidationError validationError = new ValidationError().setMessage(String.format("'%s' not valid number", numberValue));
        if (!Pattern.matches(PATTERN_NUMBER, numberValue)) {
            validatable.error(validationError);
        }
    }
}
