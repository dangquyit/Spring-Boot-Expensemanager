package com.junior.expensemanager.validator;

import com.junior.expensemanager.dto.ExpenseDTO;
import com.junior.expensemanager.util.DateTimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

@Component
public class ExpenseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExpenseDTO expenseDTO = (ExpenseDTO) target;
        if(expenseDTO.getName().equals("") || expenseDTO.getName() == null) {
            errors.rejectValue("name", "msg.empty");
        }

        if(expenseDTO.getDateString().equals("") || expenseDTO.getDateString() == null) {
            errors.rejectValue("dateString", "msg.empty");
        }

        if(expenseDTO.getAmount() == null) {
            errors.rejectValue("amount", "msg.empty");
        } else {
            if(expenseDTO.getAmount().compareTo(new BigDecimal(0)) < 1) {
                errors.rejectValue("amount", "msg.error.amount");
            }
        }

    }
}
