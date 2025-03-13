package com.yingzi.annotation.component;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yingzi
 * @date 2024/4/19 20:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintValidateAgeValidator.class)
public @interface ConstraintValidateAge {

    int min() default 18;
    int max() default 60;

    String message() default "年龄不合法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
