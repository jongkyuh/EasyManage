package com.hjk.EasyManage.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handlerIllegalArgument(IllegalArgumentException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "error/error";
    }
}
