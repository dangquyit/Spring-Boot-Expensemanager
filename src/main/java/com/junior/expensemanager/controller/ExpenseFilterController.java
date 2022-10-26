package com.junior.expensemanager.controller;

import com.junior.expensemanager.dto.ExpenseDTO;
import com.junior.expensemanager.dto.ExpenseFilterDTO;
import com.junior.expensemanager.service.ExpenseService;
import com.junior.expensemanager.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Controller
public class ExpenseFilterController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/filter-expenses")
    public String filterExpenses(@ModelAttribute("filter")ExpenseFilterDTO expenseFilterDTO,  Model model) throws ParseException {
        String keyword = expenseFilterDTO.getKeyword();
        String sortBy = expenseFilterDTO.getSortBy();
        String fromDateStr = expenseFilterDTO.getFromDate();
        String toDateStr = expenseFilterDTO.getToDate();

        Date fromDate = !fromDateStr.isEmpty() ? DateTimeUtil.convertStringToDate(fromDateStr) : new Date(0);
        Date toDate = !toDateStr.isEmpty() ? DateTimeUtil.convertStringToDate(toDateStr) : new Date(System.currentTimeMillis());
        List<ExpenseDTO> expensesDTO = expenseService.getFilterExpenses(keyword, sortBy, fromDate, toDate);
        model.addAttribute("expenses", expensesDTO);
        return "expenses-list";
    }
}
