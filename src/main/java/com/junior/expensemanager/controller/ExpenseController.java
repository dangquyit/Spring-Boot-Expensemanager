package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.ExpenseDTO;
import com.junior.expensemanager.dto.ExpenseFilterDTO;
import com.junior.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        model.addAttribute("filter", new ExpenseFilterDTO());
        model.addAttribute("expenses", expenseService.getAllExpenseList());
        return "expenses-list";
    }

    @GetMapping("/create-expense")
    public String showExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/save-or-update-expense")
    public String saveOrUpdateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }


    @GetMapping("/delete-expense")
    public String deleteExpense(@RequestParam("id") Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/update-expense")
    public String updateExpense(@RequestParam("id") Long id, Model model) throws ParseException {
        model.addAttribute("expense", expenseService.findById(id));
        return "expense-form";
    }
}
