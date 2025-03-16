package com.yingzi.thymeleaf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yingzi
 * @date 2025/3/16:16:47
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting");
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}