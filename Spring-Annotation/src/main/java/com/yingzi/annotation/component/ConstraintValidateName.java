package com.yingzi.annotation.component;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yingzi
 * @date 2024/4/20 14:02
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintValidateNameValidator.class)
public @interface ConstraintValidateName {

    String value() default "";
    String message() default "未以指定字符开头";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
