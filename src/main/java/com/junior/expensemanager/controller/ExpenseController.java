package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.ExpenseDTO;
import com.junior.expensemanager.dto.ExpenseFilterDTO;
import com.junior.expensemanager.service.ExpenseService;
import com.junior.expensemanager.validator.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseValidator expenseValidator;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) throws Exception {
        List<ExpenseDTO> expenses = expenseService.getAllExpenseList();
        model.addAttribute("filter", new ExpenseFilterDTO());
        model.addAttribute("expenses", expenses);
        model.addAttribute("total", expenseService.totalExpenses(expenses));
        return "expenses-list";
    }

    @GetMapping("/create-expense")
    public String showExpenseForm(Model model) {
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/save-or-update-expense")
    public String saveOrUpdateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO, BindingResult result) throws ParseException {
        expenseValidator.validate(expenseDTO, result);
        if(result.hasErrors()) {
            return "expense-form";
        }
        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }


    @GetMapping("/delete-expense/{expenseId}")
    public String deleteExpense(@PathVariable("expenseId") String expenseId) throws ParseException {
        expenseService.deleteExpenseByExpenseId(expenseId);
        return "redirect:/expenses";
    }

    @GetMapping("/update-expense/{expenseId}")
    public String updateExpense(@PathVariable("expenseId") String expenseId, Model model) throws ParseException {
        model.addAttribute("expense", expenseService.findByIdExpenseId(expenseId));
        return "expense-form";
    }
}
