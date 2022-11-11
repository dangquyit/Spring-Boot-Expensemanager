package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.service.UserService;
import com.junior.expensemanager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = {"/login", "/"})
    public String showLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken)  {
            return "login";
        }

        return "redirect:/expenses";
    }

    @GetMapping(value = {"/register"})
    public String showRegister(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping(value = {"/register",})
    public String saveOrUpdateUser(@ModelAttribute("user") UserDTO userDTO, Model model, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        model.addAttribute("successMsg", true);
        userService.save(userDTO);
        return "response";
    }
}