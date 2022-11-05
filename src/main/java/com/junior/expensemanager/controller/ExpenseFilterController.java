package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.ExpenseDTO;
import com.junior.expensemanager.dto.ExpenseFilterDTO;
import com.junior.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseFilterController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/filter-expenses")
    public String filterExpenses(@ModelAttribute("filter")ExpenseFilterDTO expenseFilterDTO,  Model model) throws ParseException {
        List<ExpenseDTO> expensesDTO = expenseService.getFilterExpenses(expenseFilterDTO);
        model.addAttribute("total", expenseService.totalExpenses(expensesDTO));
        model.addAttribute("expenses", expensesDTO);
        return "expenses-list";
    }
}
