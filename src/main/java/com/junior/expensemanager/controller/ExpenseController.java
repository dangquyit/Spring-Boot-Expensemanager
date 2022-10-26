package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.ExpenseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.model.IModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {
    private static List<ExpenseDTO> list = new ArrayList<>();
    static {
        ExpenseDTO exp1 =new ExpenseDTO();
        exp1.setId(1L);
        exp1.setExpenseId("1234");
        exp1.setDate(new Date(System.currentTimeMillis()));
        exp1.setAmount(BigDecimal.valueOf(300.0));
        exp1.setDescription("dasdsd");
        list.add(exp1);
    }
    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        model.addAttribute("expenses", list);
        return "expenses-list";
    }
}
