package com.yingzi.annotation.component;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author yingzi
 * @date 2024/4/19 19:38
 * 年龄校验器
 */
public class ConstraintValidateAgeValidator implements ConstraintValidator<ConstraintValidateAge, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(ConstraintValidateAge constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null) {
            return false;
        }
        return integer >= min && integer <= max;
    }

}