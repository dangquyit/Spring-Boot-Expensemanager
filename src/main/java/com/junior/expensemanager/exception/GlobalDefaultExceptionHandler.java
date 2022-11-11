package com.junior.expensemanager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler{
    @ExceptionHandler(ExpenseNotFoundException.class)
    public String handlerExpenseNotFoundException(HttpServletRequest request, Model model, ExpenseNotFoundException ex) {
        model.addAttribute("notFound", true);
        model.addAttribute("message", ex.getMessage());
        return "response";
    }

    @ExceptionHandler(Exception.class)
    public String handlerGlobalException(HttpServletRequest request, Model model, Exception ex) {
        model.addAttribute("serverError", true);
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("stackTrack", "Something went wrong! Please contact Administrator");
        return "response";
    }
}

