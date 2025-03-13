package com.yingzi.annotation.controller;

import com.yingzi.annotation.dto.UserReq;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yingzi
 * @date 2025/3/10:19:18
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("ageValid")
    public String ageValid(@Validated @RequestBody UserReq req, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            return fieldErrors.get(0).getDefaultMessage();
        }
        return "OK";
    }

    @PostMapping("nameValid")
    public String nameValid(@Validated @RequestBody UserReq req, BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            return fieldErrors.get(0).getDefaultMessage();
        }
        return "OK";
    }
}
