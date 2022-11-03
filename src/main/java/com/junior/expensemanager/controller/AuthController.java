package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping(value = {"/login", "/"})
    public String showLogin() {
        return "login";
    }

    @GetMapping(value = {"/register"})
    public String showRegister(Model model) {
    model.addAttribute("user", new UserDTO());
    return "register";
    }

    @PostMapping(value = {"/save-or-update-user"})
    public String saveOrUpdateUser(@ModelAttribute("user") UserDTO userDTO, Model model) {
        model.addAttribute("successMsg", true);
        return "login";
    }
}
