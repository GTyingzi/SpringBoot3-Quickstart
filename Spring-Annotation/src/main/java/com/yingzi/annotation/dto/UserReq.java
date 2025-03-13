package com.yingzi.annotation.dto;

import com.yingzi.annotation.component.ConstraintValidateAge;
import com.yingzi.annotation.component.ConstraintValidateName;
import lombok.Data;

/**
 * @author yingzi
 * @date 2025/3/10:19:16
 */
@Data
public class UserReq {

    @ConstraintValidateName(value = "影", message = "名字未以'影'开头")
    private String name;

    @ConstraintValidateAge(min = 20, max = 35, message = "年龄未在[20,35]")
    private int age;
}
