package com.example.appDemo.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by xu.yin on 9/8/17.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception exception, Model model){
        model.addAttribute("errorMessage", exception.getMessage());
        return "errorPage";
    }
}
