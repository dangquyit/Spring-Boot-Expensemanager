package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.UserDTO;
import com.junior.expensemanager.repository.UserRepository;
import com.junior.expensemanager.service.UserService;
import com.junior.expensemanager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/login", "/"})
    public String showLoginPage() {

        return "login";
    }

    @GetMapping(value = {"/register"})
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping(value = {"/register"})
    public String register(@ModelAttribute("user") UserDTO userDTO, Model model, BindingResult result) {
        userValidator.validate(userDTO, result);
        if(result.hasErrors()) {
            return "register";
        }
        userService.save(userDTO);
        model.addAttribute("successMsg", true);
        return "login";
    }
}
