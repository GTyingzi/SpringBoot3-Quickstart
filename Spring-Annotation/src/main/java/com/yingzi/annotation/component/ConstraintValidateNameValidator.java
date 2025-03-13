package com.yingzi.annotation.component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author yingzi
 * @date 2024/4/20 14:04
 */
public class ConstraintValidateNameValidator implements ConstraintValidator<ConstraintValidateName, String> {

    private String value;

    @Override
    public void initialize(ConstraintValidateName constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 以value开头
        if (s != null && s.startsWith(value)) {
            return true;
        }
        return false;
    }
}
